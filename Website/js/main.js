const BASEURL = "http://127.0.0.1/api/";

function checkObjectRadios() {
    var r = document.getElementsByName("object");
    var object = "";

    for(var i=0; i < r.length; i++){
        if(r[i].checked) {
            object = r[i].id;
        }
    }
    return object.split("-")[0];
}

function checkRowRadios() {
    var r = document.getElementsByName("rows");
    var rows = "";

    for(var i=0; i < r.length; i++){
        if(r[i].checked) {
            rows = r[i].id;
        }
    }
    return rows.split("-")[0];
}

$(function(){
    $("#GET-btn").click(function(){
        console.log(BASEURL + objectType + "/?rows=" + nrOfRows);

        var objectType = checkObjectRadios();
        var nrOfRows = checkRowRadios();

        $.ajax({
            type: "GET",
            url: BASEURL + objectType + "/?rows=" + nrOfRows,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                $("#response").text(JSON.stringify(data));
            },
            error: function(error) {
                console.error(error);
            }
        });
    });

    $("#POST-btn").click(function(){
        console.log(BASEURL + objectType + "/");

        var objectType = checkObjectRadios();
        var nrOfRows = checkRowRadios();
        var data = "{}"

        if (objectType == "persons") {
            data = JSON.stringify({
                "firstname": faker.name.firstName(),
                "lastname": faker.name.lastName()
            });
        }

        if (objectType == "addresses") {
            data = JSON.stringify({
                "street": faker.address.streetName(),
                "zipcode": faker.address.zipCode(),
                "city": faker.address.city(),
                "county": faker.address.county(),
                "country": faker.address.country()
            });
        }

        if (objectType == "profiles") {
            data = JSON.stringify({
                "firstname": faker.name.firstName(),
                "lastname": faker.name.lastName(),
                "phonenumber": faker.phone.phoneNumber(),
                "email": faker.internet.email(),
                "username": faker.internet.userName(),
                "password": faker.internet.password(20),
                "description": faker.lorem.sentence(),
                "website": faker.internet.url(),
                "lastip": faker.internet.ip(),
                "lastlogin": faker.date.recent()

            });
        }

        console.log(data);
        $.ajax({
            type: "POST",
            url: BASEURL + objectType + "/",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: data,
            success: function(data) {
                $("#response").text(JSON.stringify(data));
            },
            error: function(error) {
                console.error(error);
            }
        });

        $("#response").text(JSON.stringify(JSON.parse(data),null,2));
    });

    $("#DELETE-btn").click(function(){
        var objectType = checkObjectRadios();
        var nrOfRows = checkRowRadios();

        console.log(BASEURL + objectType + "/");

        $.ajax({
            type: "DELETE",
            url: BASEURL + objectType + "/",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                $("#response").text(JSON.stringify(data));
            },
            error: function(error) {
                console.error(error);
            }
        });
    });
});
