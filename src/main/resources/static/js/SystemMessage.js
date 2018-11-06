/**
 * Created by Adyina on 2018/8/21.
 */

function getCookie(name){

    var strcookie = document.cookie;
    var arrcookie = strcookie.split("; ");

    for ( var i = 0; i < arrcookie.length; i++) {
        var arr = arrcookie[i].split("=");
        if (arr[0] == name){
            return arr[1];
        }
    }
    return null;
}

function delCookie(name) {
    document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 GMT ; path=/";
}



