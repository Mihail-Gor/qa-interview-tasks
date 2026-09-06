---
id: backend-rest-assured-filters-specs
title: "REST Assured: Request/Response Specifications, кастомные фильтры и интеграция с Allure"
category: backend-automation
difficulty: medium
frequency: high
confidence: 5
last_reviewed: 2026-09-06
tags:
  - api-automation
  - rest-assured
  - filters
  - specifications
  - allure
---

# ❓ REST Assured: спецификации, фильтры и логирование

### ⚡ Краткий ответ (TL;DR для повторения за 30–60 сек)
- **Request/ResponseSpecification**: переиспользуемые шаблоны запросов/ответов (baseUri, headers, content-type, общие проверки статус-кода и схемы). Избавляют от дублирования кода (DRY).
- **Filters (`io.restassured.filter.Filter`)**: перехватчики (interceptors) жизненного цикла запроса и ответа. Позволяют модифицировать заголовки (подстановка Bearer токена, refresh токена при 401), логировать запросы в Allure (`AllureRestAssured`) или маскировать чувствительные данные (пароли, номера карт).
- **Сериализация/Десериализация**: поддержка Jackson / Gson / Lombok DTO (включая Records в Java 17+).

---

### 📖 Развернутый ответ

#### 1. Использование RequestSpecBuilder и ResponseSpecBuilder
```java
public class ApiSpecs {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.example.com")
                .setBasePath("/v1")
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured()) // Автоматическое логирование в Allure
                .addFilter(new CustomAuthFilter())  // Кастомный фильтр авторизации
                .log(LogDetail.URI)
                .build();
    }

    public static ResponseSpecification responseSpec(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .expectContentType(ContentType.JSON)
                .build();
    }
}
```

#### 2. Пример кастомного фильтра (Автоматический Refresh токена при 401)
```java
public class AutoRefreshAuthFilter implements Filter {
    private static String token;

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        if (token != null) {
            requestSpec.header("Authorization", "Bearer " + token);
        }

        Response response = ctx.next(requestSpec, responseSpec);

        if (response.statusCode() == 401) {
            token = refreshAuthToken(); // получаем новый токен
            requestSpec.removeHeader("Authorization");
            requestSpec.header("Authorization", "Bearer " + token);
            return ctx.next(requestSpec, responseSpec); // повторный запрос
        }
        return response;
    }
}
```

---

### 🔥 Каверзные вопросы на собеседовании

1. *Как скрыть пароли и токены из Allure отчетов при логировании REST Assured?*
   - **Ответ**: Настроить кастомный фильтр логирования или сконфигурировать `AllureRestAssured` / LogConfig (`config = RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization"))`).

2. *В чем разница между `extract().as(MyDto.class)` и `extract().body().jsonPath()`?*
   - `as(MyDto.class)` выполняет строгую десериализацию всего JSON в Java-объект (POJO/Record).
   - `jsonPath()` позволяет точечно извлечь одно или несколько полей по выражению без создания полного DTO.

3. *Как валидировать JSON Schema в REST Assured?*
   - **Ответ**: С помощью модуля `json-schema-validator`:
   ```java
   given().spec(requestSpec())
     .when().get("/users/1")
     .then().assertThat().body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
   ```
