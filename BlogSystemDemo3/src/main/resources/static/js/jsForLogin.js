function login() {
    let username = document.getElementById('loginUsername');
    let password = document.getElementById('loginPassword');
    if(username.value == "" || password.value == "") {
        alert("用户名和密码不能为空");
        return;
    }

    let body = {
        "username":username.value,
        "password":password.value
    }
    jQuery.ajax({
        url:"login/login",
        contentType:"application/json",
        method:"POST",
        data:JSON.stringify(body),
        success: function (data,status) {
            if (data.success != 200) {
                alert("服务器错误");
            } else {
                if (data.data == 1) {
                    location.href = "indexOj.html";
                } else {
                    alert(data.message);
                }
            }
        }
    });
}


function register() {
    let username = document.getElementById('username');
    let github = document.getElementById('github');
    let email = document.getElementById('email');
    let password = document.getElementById('password');
    let password2 = document.getElementById('password2');
    let body = {
        "username":username.value,
        "github":github.value,
        "email":email.value,
        "password1":password.value,
        "password2":password2.value
    }
    jQuery.ajax({
        url:"login/register",
        contentType:"application/json",
        method:"POST",
        data:JSON.stringify(body),
        success: function (data,status) {
            alert(data.message);
        }
    });
}