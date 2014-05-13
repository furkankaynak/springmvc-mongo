$(document).ready(function() {
    $(".update").click(function() {
        var tr= $(this).closest("tr");
        var name = $(tr).children().find(".user-name")[0];
        console.log(name);
    });
});