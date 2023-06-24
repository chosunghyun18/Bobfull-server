## 팀 초록 깡통

### 벡엔드 서버 저장소

- 모든 벡엔드 자원을 관리 하는 목적의 저장소

### 서버 실행 방법

- BobFull.jar 파일 실행

  $ java -jar -Duser.timezone=Asia/Seoul ./BobFull.jar

#### 폴더 구조

- gather : 매칭 관련 영역

- member : 회원 관련 영역

- common : 유틸성 기능 영역

  - Error Handler
  - API response Handler

- infra :
  - aws s3 : 폴더별 image handle , CRUD
  - google fcm : push alarm handle
