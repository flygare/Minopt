const BASEURL = "http://0.0.0.0:3000";

$(function(){
    $("#get-btn").click(function(){
        console.log("GET");
        $.get("http://mean-quote-machine.herokuapp.com/get_random_quote", function(data) {
            var json = JSON.parse(JSON.stringify(data));
            var author = json.quote.author;
            var text = json.quote.text;

            $("#quote-author").text(author);
            $("#quote-text").text(text);
        });
    });

    $("#post-btn").click(function(){
        console.log("POST");
    });
});
