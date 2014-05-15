$(document).ready(function() {

    $("#userSaveFormSubmit").click(function() {

        $( "#userSaveForm" ).validate({
            rules: {
                name: {
                    required: true
                },
                lastname: {
                    required: true
                },
                phone: {
                    required: true,
                    number: true,
                    min: 11
                }
            },
            submitHandler: function(form) {
                var name = $("#nameform").val();
                var lastname = $("#lastnameform").val();
                var phone = $("#phoneform").val();
                var recaptcha_challenge_field = $("#recaptcha_challenge_field").val();
                var recaptcha_response_field = $("#recaptcha_response_field").val();

                $.ajax({
                    type: "POST",
                    url: "/user",
                    data: {
                        name: name,
                        lastname: lastname,
                        phone: phone,
                        recaptcha_challenge_field: recaptcha_challenge_field,
                        recaptcha_response_field: recaptcha_response_field
                    },
                    statusCode: {
                        406: function(data) {
                            alert(data.responseText);
                        },
                        201: function() {
                            alert("User added");
                        },
                        400: function() {
                            alert("An error occurred");
                        }
                    }
                });
            }
        });

    });

    $.ajaxSetup({
        beforeSend: function(){
            console.log("Ajax call basladi");
        },
        complete: function(){
            console.log("Ajax call bitti");
        }
    });

    $(".userDelete").click(function() {
       var id = $(this).attr("data-id");
        var self = $(this);
        $.ajax({
            type: "DELETE",
            url: "/user/" + id,
            statusCode: {
                200: function() {
                    alert("User deleted");
                    $(self).closest("tr").remove();
                },
                400: function() {
                    alert("An error occurred");
                }
            }
        });
    });

    $(".userUpdate").click(function() {
        var id = $(this).attr("data-id");
        var name = $("#name-" + id).text();
        var lastname = $("#lastname-" + id).text();
        var phone = $("#phone-" + id).text();

        $("#updateidform").val(id);
        $("#updatenameform").val(name);
        $("#updatelastnameform").val(lastname);
        $("#updatephoneform").val(phone);

    });

    $("#userUpdateFormSubmit").click(function() {

        $( "#userUpdateForm" ).validate({
            rules: {
                name: {
                    required: true
                },
                lastname: {
                    required: true
                },
                phone: {
                    required: true,
                    number: true,
                    min: 11
                }
            },
            submitHandler: function(form) {
                var id = $("#updateidform").val();
                var name = $("#updatenameform").val();
                var lastname = $("#updatelastnameform").val();
                var phone = $("#updatephoneform").val();

                $.ajax({
                    type: "POST",
                    url: "/user/update",
                    data: {
                        id: id,
                        name: name,
                        lastname: lastname,
                        phone: phone
                    },
                    statusCode: {
                        200: function() {
                            alert("User updated");
                            $(this).attr("data-id", id);
                            $("#name-" + id).text(name);
                            $("#lastname-" + id).text(lastname);
                            $("#phone-" + id).text(phone);
                            $('#updateModal').modal('hide');
                        },
                        400: function() {
                            alert("An error occurred");
                        }
                    }
                });
            }
        });

    });

});