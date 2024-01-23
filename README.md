<img src="https://capsule-render.vercel.app/api?type=waving&color=2E64FE&height=150&section=header&text=WEAVUS%20쇼핑몰%20프로젝트&fontSize=25"/>

With this project, We made an Internet shopping mall.

<details>
<summary>
<img src="https://raw.githubusercontent.com/Tarikul-Islam-Anik/Animated-Fluent-Emojis/master/Emojis/Hand%20gestures/Eyes.png" alt="Eyes" width="2%" />Our Team used 
</summary>
  <br>
  
![js](https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white)
![js](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) 
![js](https://img.shields.io/badge/CSS-239120?&style=for-the-badge&logo=css3&logoColor=white) 
![js](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white) 
![js](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white) 
![js](https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![js](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)

</details>

[![Top Langs](https://github-readme-stats.vercel.app/api/top-langs/?username=JSH95&layout=donut-vertical&exclude_repo=BookRental,TestTranslatorBack,ClassManagement,test_repo,testrepo,weavusmanage)](https://github.com/anuraghazra/github-readme-stats)


# 쇼핑몰사이트

쇼핑몰사이트의 프론트는 부트스트랩과 타임리프를 사용하여 작성하였습니다.

## 사이트 상세 설명

로그인 상태에 따른 유동적인 화면을 보여줍니다.

```bash
login : 고객계정 -> 장바구니, 로그아웃, 카테고리 선택 가능
login : 관리자계정 -> 상품 추가 버튼이 추가로 선택 가능
login : Null -> 회원가입, 상품 상세 보기만 가능, 장바구니 진입 불가
```

상품 상세화면 화면에서 바로 구매와 상품 수정이 가능합니다.

```bash
login : 고객계정 -> 장바구니, 바로구매 선택 가능
login : 관리자계정 -> 상품 수정 버튼 추가로 선택가능
login : Null -> 상품 정보만 확인 가능, 바로구매, 장바구니 진입 불가
```
결제화면에서는 카드번호화 유저영어이름, cvc번호가 확인 시 결제가 완료됩니다.

```bash
data != Null -> 결제완료 메세지와 함께 장바구니 상품 삭제 후 메인페이지 이동
data == Null -> 에러메세지 표시와 함꼐 결제페이지에서 대기
```


## 상세기능

```JAVA

# 아이디 중복 처리
 User user = userMapper.findById(id);
        if (user != null){
            model.addAttribute("msg","중복된 아이디 입니다.");
        }else {
            model.addAttribute("msg","사용 가능한 아이디 입니다.");
        }
        return "idCheck";


# 로그인 계정 별 유동적 화면 처리
 if(user.getId().equals("admin")){
                    items = itemService.findByItemCategory(num);
                } else{
                    items = itemService.findByItemCategoryAndItemStatus(num, "1");
                }

# 상품 데이터 처리 방법
 findByItemCategoryAndItemStatus //카테고리와 품절 상태 동시 확인
 findByItemCategory //카테고리 확인
 findByItemStatus //품절상태 확인
 getAllItems //모든 상품의 정보 확인
 findById //해당 이름 상품 정보 확인
 saveItem //상품 저장
 updateItem //성품 수정

# 로그인 데이터 처리 방법
 findById //아이디 중복 검사
 findByIdAndPassword //로그인 정보 확인
 saveUser //회원가입

# 장바구니 데이터 처리 방법
 addItemToCart //장바구니 버튼 동작
 findCartInfoByUserId //계정 별 장바구니 정보 저장
 clearCartByUserId //구매 후 장바구니 상품 삭제

```
## 장바구니 Mapper

```XML
<insert id="addItemToCart" parameterType="com.weavus.weavusshoppingmall.entity.Cart">
        insert into cart (item_id, id)
        values (
                   #{item_id},
                   #{id}
               )
    </insert>

    <resultMap id="CartResultMap" type="com.weavus.weavusshoppingmall.entity.Cart">
        <id column="cart_id" property="cartId"/>
        <result column="id" property="id"/>
        <result column="item_id" property="itemId"/>
        <result column="cart_stock" property="cartStock"/>
        <result column="created_at" property="createdAt"/>

        <collection property="itemList" ofType="com.weavus.weavusshoppingmall.entity.Item">
            <id column="item_id" property="itemId"/>
            <result column="item_name" property="itemName"/>
            <result property="itemCategory" column="item_category" />
            <result column="item_price" property="itemPrice" />
            <result column="item_info" property="itemInfo" />
            <result property="filepath" column="filepath" />
            <result property="filename" column="filename" />
            <!-- 추가적인 Item 필드 매핑 -->
        </collection>
    </resultMap>

    <select id="findCartInfoByUserId" resultType="com.weavus.weavusshoppingmall.entity.Cart" resultMap="CartResultMap">
        SELECT c.*, i.*
        FROM cart c
                 LEFT JOIN item i ON c.item_id = i.item_id
        WHERE c.id = #{id}
    </select>

    <delete id="clearCartByUserId">
        DELETE from cart WHERE id = #{id}
    </delete>
```

## 로그인 Mapper

```XML
    <select id="findByIdAndPassword" resultType="com.weavus.weavusshoppingmall.entity.User">
        select * from user
        where id = #{id} AND password = #{password}
    </select>

    <select id="findById" resultType="com.weavus.weavusshoppingmall.entity.User">
        select * from user
        where id = #{id}
    </select>

    <insert id="saveUser" parameterType="com.weavus.weavusshoppingmall.entity.User">
        insert into user (id, password, user_email, card_number, card_cvc, user_name, user_en_name, is_active)
        values (#{id}, #{password}, #{userEmail}, #{cardNumber}, #{cardCvc}, #{userName}, #{userEnName}, #{isActive});
    </insert>
```

## 상품정보 Mapper

```XML
    <select id="findById" resultType="com.weavus.weavusshoppingmall.entity.Item">
        select item_id as itemId, item_name as itemName, item_category as       itemCategory, item_info as itemInfo, item_price as itemPrice, item_status as         itemStatus, filepath, filename
        from item
        where item_id = #{itemId}
    </select>


    <insert id="saveItem" parameterType="com.weavus.weavusshoppingmall.entity.Item">
        insert into item (item_id, item_name, item_category, item_info, item_price, item_status, filepath, filename)
        values (null, #{itemName}, #{itemCategory}, #{itemInfo}, #{itemPrice}, #{itemStatus}, #{filepath}, #{filename});
    </insert>

    <insert id="updateItem" parameterType="com.weavus.weavusshoppingmall.entity.Item">
        update `item` set `item_name` = #{itemName}, `item_category` = #{itemCategory}, `item_info` = #{itemInfo}, `item_price` = #{itemPrice}, `item_status` = #{itemStatus}
        WHERE `item`.`item_id` = #{itemId}
    </insert>

    <select id="findByItemStatus" resultType="com.weavus.weavusshoppingmall.entity.Item">
    select * from item where item_status = #{status}
    </select>

    <select id="findByItemCategoryAndItemStatus" resultType="com.weavus.weavusshoppingmall.entity.Item">
        select item_id as itemId, item_name as itemName, item_category as itemCategory, item_info as itemInfo, item_price as itemPrice, item_status as itemStatus, filepath, filename
        from item
        where item_category = #{num} AND item_status = #{status}
    </select>

    <select id="getAllItems" resultType="com.weavus.weavusshoppingmall.entity.Item">
        select * from item
    </select>

    <select id="findByItemCategory" resultType="com.weavus.weavusshoppingmall.entity.Item">
        select item_id as itemId, item_name as itemName, item_category as itemCategory, item_info as itemInfo, item_price as itemPrice, item_status as itemStatus, filepath, filename
        from item
        where item_category = #{num}
    </select>
```
