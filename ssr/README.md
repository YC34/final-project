# SPRING SECUIRTY + JSP 

## 프로젝트 의도
    seucirty 구조 이해를 위한 커스터마이징.
    spring boot는 jar로 build 되지만, 
    JSP 적용을 위해 war로 build 하고 있다.
### 패키지 구조
    JAVA
    * config - handler(디렉토리) 
                        - CustomFailureHandler (로그인 실패시 handler)
                        - CustomSuccessHandler (로그인 성공시 handler)
            - SecuirtyConfig (form login cofing)
            - WebConfig (allow할 메소드와 포트 설정 가능 : react등 다른 서버와 데이터 공유시 사용 CORS)

    * controller - 각 기능별 폴더를 만들어 진행.        
    * dao - mybatis와 연결되는 interface 
    * entity - DB와 밀접한 관련이 있는 객체
    * service - 비지니스 로직
    * JspApplication : main application
    
    resoucre
     * mapper
        - 각 기능에 맞는 xml파일 실질적인 데이터
     * application.yml
     * application-dev.yml 개발
     * application-prod.yml 실제 운영
    webapp
     * view단을 반환하는 파일들 JSP 파일
    