// //加载个人信息
// function loadSysUser() {
//     $.ajax({
//         async: false,
//         type: "POST",
//         data: {uid: id},
//         url: '/userInfo/',
//         dataType: "json",
//         success: function (data) {
//             // $("#selectRoleForm").empty();
//             // var htm = "<input type='hidden' name='uid' value='" + id + "'>";
//             // for (var i = 0; i < data.length; i++) {
//             //     htm += "<div class='checkbox'><label><input type='checkbox' name='id' value='" + data[i].id + "'";
//             //     if (data[i].selected == 1) {
//             //         htm += " checked='checked'";
//             //     }
//             //     htm += "/>" + data[i].description + "</label></div>";
//             // }
//             $(".userInfo").empty();
//             $(".userInfo").append("<input type='hidden' name='uid' id='uid' value='" + id + "'>");
//             $("#roleTable tr:has(td)").remove();
//             var html = "";
//             for (var i = 0; i < data.length; i++) {
//                 html += "<tr><td class='text-center'><input type='checkbox' class='checkRole'  name='roleId' value='" + data[i].id + "'"
//                 if (data[i].selected == 1) {
//                     html += " checked='checked'";
//                 }
//                 //角色是否可分配
//                 if (data[i].available == 0) {
//                     html += "disabled ";
//                 }
//                 html += "/>" + "</td><td class='text-center'>" + data[i].id + "</td><td class='text-center'>" + data[i].role + "</td><td class='text-center'>" + data[i].description + "</td></tr>";
//             }
//             $("#roleTable").append(html)
//         }
//     });
//     $('#selectRole').modal();
//
// }
// $(document).ready(function () {
//     $('ul.nav > li >a').click(function (e) {
//         e.preventDefault();
//         $('ul.nav > li').removeClass('active');
//         $(this).parent().addClass('active');
//         return true;
//     });
// });

//清空文本框内容
function clearForm(form) {
    // input清空
    $(':input', form).each(function () {
        var type = this.type;
        var tag = this.tagName.toLowerCase(); // normalize case
        if (type == 'text' || type == 'password' || type == 'hidden' || type == 'file' || tag == 'textarea')
            this.value = "";
        // 多选checkboxes清空
        // select 下拉框清空
        else if (tag == 'select')
            this.selectedIndex = -1;
    });
};