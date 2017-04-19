const BASEURL = "http://0.0.0.0:3000";

$(function(){
    $("#get-btn").click(function(){
        console.log("GET");
        $.ajax({
            type: "GET",
            url: "http://mean-quote-machine.herokuapp.com/get_random_quote",
            contentType: "application/json; charset=utf-8",
            dataType: "jsonp",
            success: function(data) {
                var json = JSON.parse(JSON.stringify(data));
                var author = json.quote.author;
                var text = json.quote.text;

                $("#quote-author").text(author);
                $("#quote-text").text(text);
            },
            error: function(error) {
                console.error(error);
            }
        });
    });

    $("#post-btn").click(function(){
        console.log("POST");
        var tmp = JSON.stringify({
            "author": "Robin Flygare and Anthon Holmqvist",
            "text": "Minopt"
        });

        console.log(tmp);

        $.ajax({
            type: "POST",
            url: "http://localhost:1880/",
            contentType: "application/json; charset=utf-8",
            dataType: "jsonp",
            data: tmp,
            success: function(data) {
                var json = JSON.parse(JSON.stringify(data));
                var author = json.quote.author;
                var text = json.quote.text;

                $("#quote-author").text(author);
                $("#quote-text").text(text);
            },
            error: function(error) {
                console.error(error);
            }
        });
    });
});
