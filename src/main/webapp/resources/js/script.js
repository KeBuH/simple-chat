$(document).ready(function () {

    $(".dropdown-menu a").click(function(){
        $("#dropdownMenuButton:first-child").html($(this).text()+' <span class="caret"></span>');
    });

});

/**
 * This function send username to server and redirect if if result is success.
 */
function signin() {
    var login = $("#login").val();
    var color = $('#dropdownMenuButton').find(":selected").attr('value');
    var lable = $("#signinError").css({'visibility': 'hidden'});
    if (login !== '') {
        $.ajax({
            url: "/signin",
            method: "POST",
            data: { username: login, color: color },
            success: function () {
                window.location =
                    "http://" + location.hostname + ":" +
                    location.port +"/?username=" + login;
            },
            error: function () {
                lable.css({'visibility': 'visible'});
            }
        });

    }
}

/**
 * This function send logout action to senver.
 */
function logout() {
    $.ajax({
        url: "/signout",
        method: "GET",
        success: function () {
        }
    });
}


/**
 * This function send new message.
 */
function sendMessage() {
    var inputMessage = $("#inputName");
    $.ajax({
       url: "/send",
       method: "POST",
       data: {message: inputMessage.val()},
       success: function () {
           inputMessage.val('');
           refresh();
       }
    });
}

/**
 * This fucntion render new messages to list.
 */
function printMessages() {
    var index = $("#chatArea").find(".text").length;
    $.ajax({
        url: "/items",
        method: "GET",
        data: {index: index},
        success: function (json) {
            if (json.length > 0) {
                for (var count = 0; count < json.length; count++) {
                    $("#chatArea").append(
                        "<p class='text' style='word-break: break-all; color: "
                        + json[count].user.color + "'>"
                        + new Date(json[count].date).toLocaleTimeString() + " " +
                        json[count].user.username + ": "
                        + json[count].message + "</p>")
                }
            }
        }
    });
}


