init();
function init() {
    $.ajax({
        url:"blog/getList",
        method:"GET",
        success:function (data,status) {
            let odd = document.querySelector(".odd");
            if (odd != null) {
                odd.remove();
            }

            let row = document.querySelector("#dataTables-example_wrapper .row");
            if (row != null) {
                row.remove();
            }


            let list = document.getElementById('list');
            list.innerHTML = "";
            for (let datum of data) {
                let tr = document.createElement('tr');
                list.appendChild(tr);
                let td1 = document.createElement('td');
                let a = document.createElement('a');
                a.innerHTML = datum.title;
                a.href = "blog.html?blogId=" + datum.blogId;
                td1.appendChild(a);
                tr.appendChild(td1);
                let td2 = document.createElement('td');
                td2.innerHTML = datum.username;
                tr.appendChild(td2);
                let td3 = document.createElement('td');
                td3.innerHTML = transformTimestamp(datum.postTime);
                tr.appendChild(td3);
                let td4 = document.createElement('td');

                td4.innerHTML = datum.status;

                tr.appendChild(td4)
                let td5 = document.createElement('td');
                let button1 = document.createElement('input');
                button1.type = "button";
                if (datum.status == "是") {
                    button1.value = "恢复";
                } else {
                    button1.value = "删除";
                }
                button1.onclick = function () {
                    let url = null;
                    if (datum.status == "是") {
                        url = "blog/recoverBlog?blogId=";
                    } else {
                        url = "blog/deleteBlogDesc?blogId=";
                    }
                    $.ajax({
                        url: url + datum.blogId,
                        method: "GET",
                        success:function (data,status) {
                            if (data.success != 200) {
                                alert("服务器错误");
                            } else {
                                alert(data.message);
                                if (data.data == 1) {
                                    init();
                                }
                            }
                        }
                    })
                }
                td5.appendChild(button1);
                tr.appendChild(td5)
                let td6 = document.createElement('td');
                let button2 = document.createElement('input');
                button2.type = "button";
                button2.value = "彻底删除";
                button2.onclick = function () {
                    $.ajax({
                        url: "blog/deleteBlog?blogId=" + datum.blogId,
                        method: "GET",
                        success:function (data,status) {
                            if (data.success != 200) {
                                alert("服务器错误");
                            } else {
                                alert(data.message);
                                if (data.data == 1) {
                                    init();
                                }
                            }
                        }
                    })
                }
                td6.appendChild(button2);
                tr.appendChild(td6)
                list.appendChild(tr);
            }
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