const BASEURL = "https://jsonplaceholder.typicode.com";
$(function(){
    $("#get-btn").click(function(){
        console.log("GET");
        $.ajax({
            type: "GET",
            url: BASEURL + "/posts/1",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data) {
                var json = JSON.parse(JSON.stringify(data));

                $("#get-title").text(json.title);
                $("#get-body").text(json.body);
            },
            error: function(error) {
                console.error(error);
            }
        });
    });
    
    $('body').on('click','.option li',function(){
        var i = $(this).parents('.select').attr('id');
            var v = $(this).children().text();
                var o = $(this).attr('id');
                    $('#'+i+' .selected').attr('id',o);
                        $('#'+i+' .selected').text(v);
    });
    
    $("#post-btn").click(function(){
        console.log("POST");
        var lorem = JSON.stringify({
            "title": "Hello World",
            "body": "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
        });

        $.ajax({
            type: "POST",
            url: BASEURL + "/posts",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: lorem,
            success: function(data) {
                var json = JSON.parse(JSON.stringify(data));

                $("#post-title").text(json.title);
                $("#post-body").text(json.body);
            },
            error: function(error) {
                console.error(error);
            }
        });
    });
});
