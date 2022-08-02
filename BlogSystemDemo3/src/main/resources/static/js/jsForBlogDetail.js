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


let ifGetDiscuss = 0;

function sutmitDiscuss() {
    var params = parseQueryString();
    let blogId = params["blogId"];
    let content = document.getElementById('exampleFormControlTextarea1');
    let body = {
        "blogId":blogId,
        "content":content.value
    };
    $.ajax({
        url:"discuss/addDiscuss",
        method:"POST",
        contentType:"application/json",
        data:JSON.stringify(body),
        success:function(data,status) {
            alert(data.message);
            if(data.data == 1) {
                content.value = "";
            }
            getDiscusses();
            getDiscusses();
        }
    })

    
}

function getDiscusses() {
    $.ajax({
        url:"discuss/getDiscuss" + location.search,
        method:"GET",
        success: function(data,status) {
            buildDiscuss(data.data);
        }
    })
}

function buildDiscuss(data) {
    let discusses = document.getElementById('discusses');
    if(ifGetDiscuss == 1) {
        ifGetDiscuss = 0;
        discusses.innerHTML = "";
        return;
    }
    ifGetDiscuss = 1;

    let h1 = document.createElement('h1');
    h1.innerHTML = "评论";
    let container = document.createElement('div');
    container.className = "container";
    discusses.appendChild(h1);
    discusses.appendChild(container);
    let messageDiv = document.createElement('div');
    messageDiv.className = "jumbotron";
    discusses.appendChild(messageDiv);
    for (let message of data) {
        let hr = document.createElement('hr');
        messageDiv.appendChild(hr);
        let username = document.createElement('h5');
        let dateDiv = document.createElement('h6')
        let discuss = document.createElement('p');
        //名字
        username.innerHTML = message.username;
        messageDiv.appendChild(username);
        // 构建发布日期
        // transformTimestamp
        dateDiv.innerHTML = transformTimestamp(message.postTime);
        messageDiv.appendChild(dateDiv);
        // 构造评论的信息。
        discuss.innerHTML = message.content;
        messageDiv.appendChild(discuss);

        // 判断是不是自己的评论，如果是就添加删除按键
        if (message.isYourDiscuss == 1) {
            let deleteButton = document.createElement('button');
            deleteButton.className = 'btn btn-secondary btn-sm';
            deleteButton.innerHTML = "删除";
            deleteButton.onclick = function () {
                jQuery.ajax({
                    url: 'discuss/deleteDiscuss?discussId=' + message.discussId,
                    method: 'get',
                    success: function (data,status) {
                        alert(data.message);
                        if (data.data == 1) {
                            getDiscusses();
                            if(ifGetDiscuss == 0) {
                                ifGetDiscuss = 1;
                            } else {
                                discusses.innerHTML = "";
                                ifGetDiscuss = 0;
                            }
                        }
                    }
                })
            }
            messageDiv.append(deleteButton);
        }
        messageDiv.appendChild(hr);
        // 把构造好的 blogDiv 给插入到 父元素 中.
        container.appendChild(messageDiv);
    }

}

$.ajax({
    url:"blog/getBlog" + location.search,
    method:"GET",
    success: function(data, status) {
        buildBlog(data);
        buildCard(data.userId);
    }
});





function buildBlog(data) {
    //    把 title 填到 h3 标签中.
    let titleH3 = document.getElementById('title');
    titleH3.innerHTML = data.title;
    let date = document.getElementById('date');
    date.innerHTML = transformTimestamp(data.postTime);

    //    设置正文. 正文要放到 #content 这个元素中.
    //    但是此处不要直接放. 要通过 markdown 进行渲染.
    editormd.markdownToHTML('content', {markdown: data.content});
    let likes = document.getElementById('likes');
    likes.innerHTML = data.likes;
    if(data.isYourBlog == 1) {
        let buttons = document.getElementById('theButtons');
        let button1 = document.createElement('button');
        button1.type = 'button';
        button1.className = "btn btn-outline-info";
        button1.onclick = function () {
            location.href = "editor.html" + location.search; 
        }
        button1.innerHTML = '修改';
        buttons.append(button1);

        let button2 = document.createElement('button');
        button2.type = 'button';
        button2.className = "btn btn-outline-secondary";
        button2.onclick = function() {
            $.ajax({
                url:"blog/deleteBlog" + location.search,
                method:"GET",
                success: function (data,status) {
                    alert(data.message);
                    if (data.success != 200) {
                        alert("服务器错误");
                    } else {
                        alert(data.message);
                        if (data.data == 1) {
                            location.href = "list.html";
                        }
                    }
                }
            });
        }
        button2.innerHTML = '删除';
        buttons.append(button2);
    } 
}



function buildCard(userId) {
    $.ajax({
        url: "user/userMessage?userId=" + userId,
        method:"GET",
        success:function(data,status) {
            let author = document.getElementById('author');
            author.innerHTML = data.username;
            let number = document.getElementById('number');
            number.innerHTML = data.number;
            let github = document.getElementById('github');
            github.href = data.github;
            let container = document.getElementById('container');
            container.href = "authorBlogs.html?userId=" + data.userId;
        }
    })
}

function myLikes() {
    $.ajax({
        url:"blog/likes" + location.search,
        method:"GET",
        success: function(data, status) {
            let likes = document.getElementById('likes');
            likes.innerHTML = data;
        }
    })
}

function transformTimestamp(timestamp) {
    let a = new Date(timestamp).getTime();
    const date = new Date(a);
    const Y = date.getFullYear() + '-';
    const M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
    const D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + '  ';
    const h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
    const m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes());
    // const s = date.getSeconds(); // 秒
    const dateString = Y + M + D + h + m;
    // console.log('dateString', dateString); // > dateString 2021-07-06 14:23
    return dateString;
  }