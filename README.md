# Bver Backend Project

## 커밋 컨벤션

- merge: 풀리퀘스트 합병에 대한 커밋
- feat : 새로운 기능에 대한 커밋
- fix : 버그 수정에 대한 커밋
- build : 빌드 관련 파일 수정에 대한 커밋
- chore : 그 외 자잘한 수정에 대한 커밋
- docs : 문서 수정에 대한 커밋
- refactor : 코드 리팩토링에 대한 커밋
- test : 테스트 코드 수정에 대한 커밋

## 브랜치 전략
사용할거라면 토의 후에 결정

## entity 디렉토리
Member, Bakery, Reservation 등과 같은 데이터 베이스 엔티티들

## dto 디렉토리
클라이언트와의 통신 시 보낼 객체들

크게는 엔티티들로 구분하고 그 안에서는 Response, Request로 구분

## common 디렉토리

### BaseResponse, BaseResponseStatus, BaseException
프론트로 응답을 보내기 위한 클래스들.

**좋은 방법이 아니라고 생각하시면 ResponseEntity<T> 사용해도 괜찮습니다.**
```java
//회원가입 시
{
    "isSuccess": true,
    "code": 200,
    "message": "요청에 성공하였습니다.",
    "result": "회원가입에 성공하였습니다."
}

//userIdx를 통해 user를 조회 시
{
    "isSuccess": true,
    "code": 200,
    "message": "요청에 성공하였습니다.",
    "result": {
        "email": "ldc991104@gmail.com",
        "name": "이동찬",
        "age": 25
    }
}
```
위와 같이 클라이언트 측에서 읽기 쉽도록 요청에 성공했는지(isSuccess), HttpStatusCode(Code), 결과값 (Result) 를 보낼 수 있음.

#### 코드 사용 에시

##### 1. 요청에 성공해 GetUserRes.class라는 DTO를 result로 반환
```java
// UserController.class
@GetMapping(value = "/", params = "userId")
    public BaseResponse<GetUserRes> getUserById(@RequestParam Long userId) { 
        GetUserRes userRes = userService.getUserById(userId);
        return new BaseResponse<>(userRes);
    }
```
- getUserById 라는 메서드는 반환하는 값으로 BaseResponse\<GetUserRes>를 반환함.
- BaseResponse의 result 타입은 GetUserRes가 된다.
- UserService클래스의 getUserById 메서드를 통하여 GetUserRes의 객체를 만든다.
- 생성자를 통하여 GetUserRes의 객체를 파라미터로 입력해 BaseResponse라는 객체를 생성하고 반환한다.

<br>

##### 2. UserService.class의 getUserById 메서드 안에서 예외 발생 시키기.
```java
// UserService.class
public GetUserRes getUserById(Long userId) throws BaseException {
        //아이디로 유저 조회, 만약 해당 아이디의 유저가 없으면 optionalUser는 null.
        Optional<User> optionalUser = userRepository.findById(userId);

        // 만약 유저 아이디에 따른 유저가 없다면 예외 발생
        if (optionalUser.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NON_EXIST_USER);
        }

        User user = optionalUser.get();
        GetUserRes getUserRes = new GetUserRes(user);
        return getUserRes;
    }
```
- 만약 optionalUser가 null이라면 클라이언트에서 서버에게 잘못된 userId를 주었으므로 예외를 발생시킴.
- BaseResponseStatus의 Enum 상수를 파라미터로 입력해 BaseException의 생성자를 호출해 예외 객체를 생성.
- throw를 통해서 예외를 발생시킴.

<br>

##### 3. 예외가 발생하였을 때 클라이언트에게 응답 보내기
```java
// UserController.class
@GetMapping(value = "/", params = "userId")
    public BaseResponse<GetUserRes> getUserById(@RequestParam Long userId) {
        try {
            GetUserRes userRes = userService.getUserById(userId);
            return new BaseResponse<>(userRes);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
```

- try-catch구문을 통해 예외처리를 할 수 있음.
- UserService 클래스의 getUserById 메서드 실행 도중 BaseException 발생해 catch 구문이 실행 됨.
- e.getStatus()를 이용해 발생한 BaseException의 BaseResponseStatus를 가져올 수 있음. (즉 e.getStatus()는 BaseResponseStatus의 객체.)
- BaseResponseStatus를 파라미터로 BaseResponse의 생성자에 입력 시 예외의 코드, 메시지를 필드의 값으로 하는 객체 생성되어 반환됨. (BaseResponse.class의 2번 생성자 호출.)