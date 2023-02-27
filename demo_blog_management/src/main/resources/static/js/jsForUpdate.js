let editor1 = initAce1();
let editor2 = initAce2();
function initAce1() {
    // 参数 editor 就对应到刚才在 html 里加的那个 div 的 id
    let editor1 = ace.edit("editor1");
    editor1.setOptions({
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: true
    });
    editor1.setTheme("ace/theme/twilight");
    editor1.session.setMode("ace/mode/java");
    editor1.resize();
    return editor1;
}


function initAce2() {
    // 参数 editor 就对应到刚才在 html 里加的那个 div 的 id
    let editor2 = ace.edit("editor2");
    editor2.setOptions({
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: true
    });
    editor2.setTheme("ace/theme/twilight");
    editor2.session.setMode("ace/mode/java");
    editor2.resize();
    return editor2;
}

$.ajax({
    url: "problem/getProblem" + location.search,
    method: "GET",
    success:function (data,status) {
        let title = document.getElementById("title");
        let easy = document.getElementById('optionsRadios1');
        let mid = document.getElementById('optionsRadios2');
        let diff = document.getElementById('optionsRadios3');
        let description = document.getElementById('description');
        title.value = data.title;
        if (data.level == "简单") {
            easy.checked = true;
        } else if (data.level == "中等") {
            mid.checked = true;
        } else if (data.level == "困难") {
            diff.checked = true;
        }
        description.value = data.description;
        editor1.setValue(data.templateCode);
        editor2.setValue(data.testCode);
    }

})


function submitProblem() {
    let title = document.getElementById("title");
    if (title.value == "" || title.value == null) {
        alert("题目信息不全");
        return;
    }
    let easy = document.getElementById('optionsRadios1');
    let mid = document.getElementById('optionsRadios2');
    let diff = document.getElementById('optionsRadios3');
    let description = document.getElementById('description');
    if (description.value == "" || description.value == null) {
        alert("题目信息不全");
        return;
    }
    if (editor1.getValue() == "" || editor1.getValue() == null || editor2.getValue() == "" || editor2.getValue() == null) {
        alert("题目信息不全");
        return;
    }
    let level = null;
    if (easy.checked) {
        level = "简单";
    }
    if (mid.checked) {
        level = "中等";
    }
    if (diff.checked) {
        level = "困难";
    }
    let body = {
        id: params["id"],
        title:title.value,
        level: level,
        description: description.value,
        templateCode: editor1.getValue(),
        testCode: editor2.getValue()
    }
    //console.log(JSON.stringify(body));
    $.ajax({
        url:"problem/updateProblem",
        method:"POST",
        contentType:"application/json",
        data:JSON.stringify(body),
        success: function (data,status) {
            if (data.success != 200) {
                alert("服务器错误");
            } else {
                alert(data.message);
                location.href = "tableOj.html";
            }
        }
    })
}

function reset() {
    let title = document.getElementById("title");
    let description = document.getElementById('description')
    let easy = document.getElementById('optionsRadios1');
    let mid = document.getElementById('optionsRadios2');
    let diff = document.getElementById('optionsRadios3');
    title.value = "";
    description.value = "";
    easy.checked = true;
    mid.checked = false;
    diff.checked = false;
    //console.log(easy.checked,mid.checked,diff.checked);
    editor1.setValue("");
    editor2.setValue("");
}

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

