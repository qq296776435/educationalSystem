
$("#loginForm").submit(function () {
    var user = $("#user_name").val();
    var password = $("#user_password").val();
    if(user=="") {
        alert("用户名不可为空!");
    } else if(password==""){
        alert("密码不可为空!");
    } else {
        $.ajax({
            async: false,//同步，待请求完毕后再执行后面的代码
            type: "POST",
            url: 'loginVerify',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: $("#loginForm").serialize(),
            dataType: "json",
            success: function (data) {
                if(data.code==0) {
                    alert(data.msg);
                }else if (data.code=="1"){
                    $("#loginForm").attr("action","index");

                }else if (data.code=="2"){
                    $("#loginForm").attr("action","admin");
                }
            },
            error: function () {
                alert("数据获取失败")
            }
        })
    }
})