// GET Request
// https://postman-echo.com/get?foo1=bar1&foo2=bar2
pm.test("response is ok", function () {
    pm.response.to.have.status(200);
});

// pm.test("response body has json with request queries", function () {
//     pm.response.to.have.jsonBody('args.foo1', 'bar1')
//         .and.have.jsonBody('args.foo2', 'bar2');
// });


pm.test("Response body matches expected", function() {
    const response = pm.response.json();
    const expected = {
        args: {
            foo1: "bar1",
            foo2: "bar2"
        },
        headers: {
            "x-forwarded-proto": "https",
            "x-forwarded-port": "443",
            host: "postman-echo.com",
            accept: "*/*",
            "user-agent": "PostmanRuntime/7.29.0",
            "postman-token": pm.response.json().headers["postman-token"],
            "accept-encoding": "gzip, deflate, br",
            "cache-control": "no-cache"
        },
        url: "https://postman-echo.com/get?foo1=bar1&foo2=bar2"
    };
    
    // Проверка основных полей
    pm.expect(response.args).to.eql(expected.args);
    pm.expect(response.url).to.eql(expected.url);
    
    // Проверка заголовков
    pm.expect(response.headers["x-forwarded-proto"]).to.eql(expected.headers["x-forwarded-proto"]);
    pm.expect(response.headers.host).to.eql(expected.headers.host);
    pm.expect(response.headers.accept).to.eql(expected.headers.accept);
});



// POST Raw Text
// https://postman-echo.com/post
pm.test("response is ok", function () {
    pm.response.to.have.status(200);
});

// pm.test("response body has json with request body", function () {
//     pm.response.to.have.jsonBody('data', 
//         'This is expected to be sent back as part of response body.');
// });


pm.test("Response body matches expected", function() {
    const response = pm.response.json();
    const expected = {
        args: {},
        data: "This is expected to be sent back as part of response body.",
        files: {},
        form: {},
        headers: {
            "x-forwarded-proto": "https",
            "x-forwarded-port": "443",
            host: "postman-echo.com",
            "content-length": "58",
            "content-type": "text/plain",
            accept: "*/*",
            "user-agent": "PostmanRuntime/7.29.0",
            "postman-token": pm.response.json().headers["postman-token"],
            "accept-encoding": "gzip, deflate, br",
            "cache-control": "no-cache"
        },
        json: null,
        url: "https://postman-echo.com/post"
    };
    
    pm.expect(response.data).to.eql(expected.data);
    pm.expect(response.url).to.eql(expected.url);
    pm.expect(response.headers["content-type"]).to.eql(expected.headers["content-type"]);
    pm.expect(response.headers.host).to.eql(expected.headers.host);
});


// POST Form Data
// https://postman-echo.com/post
pm.test("response is ok", function () {
    pm.response.to.have.status(200);
});

// pm.test("response body has json with form data", function () {
//     pm.response.to.have.jsonBody('form.foo1', 'bar1')
//         .and.have.jsonBody('form.foo2', 'bar2');
// });


pm.test("Response body matches expected", function() {
    const response = pm.response.json();
    const expected = {
        args: {},
        data: "",
        files: {},
        form: {
            foo1: "bar1",
            foo2: "bar2"
        },
        headers: {
            "x-forwarded-proto": "https",
            "x-forwarded-port": "443",
            host: "postman-echo.com",
            "content-length": "16",
            "content-type": "application/x-www-form-urlencoded",
            accept: "*/*",
            "user-agent": "PostmanRuntime/7.29.0",
            "postman-token": pm.response.json().headers["postman-token"],
            "accept-encoding": "gzip, deflate, br",
            "cache-control": "no-cache"
        },
        json: null,
        url: "https://postman-echo.com/post"
    };
    
    pm.expect(response.form).to.eql(expected.form);
    pm.expect(response.url).to.eql(expected.url);
    pm.expect(response.headers["content-type"]).to.eql(expected.headers["content-type"]);
});


// PUT Request
// https://postman-echo.com/put
pm.test("response is ok", function () {
    pm.response.to.have.status(200);
});

// pm.test("response body has json with form data", function () {
//     pm.response.to.have.jsonBody('data', 
//         'This is expected to be sent back as part of response body.');
// });

pm.test("Response body matches expected", function() {
    const response = pm.response.json();
    const expected = {
        args: {},
        data: "This is expected to be sent back as part of response body.",
        files: {},
        form: {},
        headers: {
            "x-forwarded-proto": "https",
            "x-forwarded-port": "443",
            host: "postman-echo.com",
            "content-length": "58",
            "content-type": "text/plain",
            accept: "*/*",
            "user-agent": "PostmanRuntime/7.29.0",
            "postman-token": pm.response.json().headers["postman-token"],
            "accept-encoding": "gzip, deflate, br",
            "cache-control": "no-cache"
        },
        json: null,
        url: "https://postman-echo.com/put"
    };
    
    pm.expect(response.data).to.eql(expected.data);
    pm.expect(response.url).to.eql(expected.url);
    pm.expect(response.headers["content-type"]).to.eql(expected.headers["content-type"]);
});



// PATCH Request
// https://postman-echo.com/patch
pm.test("response is ok", function () {
    pm.response.to.have.status(200);
});

// pm.test("response body has json with form data", function () {
//     pm.response.to.have.jsonBody('data', 
//         'This is expected to be sent back as part of response body.');
// });


pm.test("Response body matches expected", function() {
    const response = pm.response.json();
    const expected = {
        args: {},
        data: "This is expected to be sent back as part of response body.",
        files: {},
        form: {},
        headers: {
            "x-forwarded-proto": "https",
            "x-forwarded-port": "443",
            host: "postman-echo.com",
            "content-length": "58",
            "content-type": "text/plain",
            accept: "*/*",
            "user-agent": "PostmanRuntime/7.29.0",
            "postman-token": pm.response.json().headers["postman-token"],
            "accept-encoding": "gzip, deflate, br",
            "cache-control": "no-cache"
        },
        json: null,
        url: "https://postman-echo.com/patch"
    };
    
    pm.expect(response.data).to.eql(expected.data);
    pm.expect(response.url).to.eql(expected.url);
    pm.expect(response.headers["content-type"]).to.eql(expected.headers["content-type"]);
});



// DELETE Request
// https://postman-echo.com/delete
pm.test("response is ok", function () {
    pm.response.to.have.status(200);
});

// pm.test("response body has json with form data", function () {
//     pm.response.to.have.jsonBody('data', 
//         'This is expected to be sent back as part of response body.');
// });

pm.test("Response body matches expected", function() {
    const response = pm.response.json();
    const expected = {
        args: {},
        data: "This is expected to be sent back as part of response body.",
        files: {},
        form: {},
        headers: {
            "x-forwarded-proto": "https",
            "x-forwarded-port": "443",
            host: "postman-echo.com",
            "content-length": "58",
            "content-type": "text/plain",
            accept: "*/*",
            "user-agent": "PostmanRuntime/7.29.0",
            "postman-token": pm.response.json().headers["postman-token"],
            "accept-encoding": "gzip, deflate, br",
            "cache-control": "no-cache"
        },
        json: null,
        url: "https://postman-echo.com/delete"
    };
    
    pm.expect(response.data).to.eql(expected.data);
    pm.expect(response.url).to.eql(expected.url);
    pm.expect(response.headers["content-type"]).to.eql(expected.headers["content-type"]);
});