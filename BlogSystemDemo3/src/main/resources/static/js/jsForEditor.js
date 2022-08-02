var parseQueryString = function() {

    var str = window.location.search;
    var objURL = {};

    str.replace(
        new RegExp( "([^?=&]+)(=([^&]*))?", "g" ),
        function( $0, $1, $2, $3 ){
            objURL[ $1 ] = $3;
        }
    );
    return objURL;
};

//Example how to use it: 
var params = parseQueryString();
let blogId = params["blogId"];


let markdownContent = "# 在这里写下一篇博客";

if(blogId != null) {
    console.log("修改");
    // ajax 获得博客内容
    $.ajax({
        async: false,
        url: "blog/getBlog" + location.search,
        method: "GET",
        success:function (data,status) {
            markdownContent = data.content;
            let exampleFormControlInput1 = document.getElementById('exampleFormControlInput1');
            console.log(data.title);
            exampleFormControlInput1.value = data.title;
        }
    });
}


var editor = editormd("editor",{
    width:"100%",
    height:"calc(560px)",   
    markdown: markdownContent,
    path:"editor.md/lib/",
    saveHTMLToTextarea:true,
    theme : "dark",
    previewTheme : "dark",
    editorTheme : 'lesser-dark'
});
let content = document.getElementById('content');

function mySubmit() {
    let title = document.getElementById('exampleFormControlInput1');
    let content = document.getElementById('content');

    if(blogId != null) {
        // 提交给blog/revise
        let blogId = params["blogId"];
        console.log(blogId);
        updateBlog(blogId,title.value,content.value);
    } else {
        // 提交给blog/newblog
        console.log("提交");
        newBlog(title.value,content.value);
    }
}


function updateBlog(blogId,title, content) {
    let body = {
        "blogId":blogId,
        "title":title,
        "content":content
    };
    $.ajax({
        url:"blog/updateBlog",
        method:"POST",
        contentType:"application/json",
        data:JSON.stringify(body),
        success: function (data,status) {
            if (data.success != 200) {
                alert("服务器错误");
            } else {
                alert(data.message);
                if (data.data == 1) {
                    location.href = "list.html";
                }
            }
        }

    })
}

function newBlog(title, content) {
    let body = {
        "title":title,
        "content":content
    }
    $.ajax({
        url:"blog/addBlog",
        method:"POST",
        contentType:"application/json",
        data:JSON.stringify(body),
        success: function (data,status) {
            if (data.success != 200) {
                alert("服务器错误");
            } else {
                alert(data.message);
                if (data.data == 1) {
                    location.href = "list.html";
                }
            }
        }

    })
}