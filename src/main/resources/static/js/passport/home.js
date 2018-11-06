/**
 * Created by Adyina on 2018/8/26.
 */
function show(id) {

    var divs = document.getElementById("menu").getElementsByTagName("div");

    for (var i = 0; i < divs.length; i++) {
        divs[i].style.display = "none";
        if (id == divs[i].id) {
            document.getElementById(id).style.display = "block";
        }
    }

}

document.getElementById("file").onchange = function() {
    document.getElementById("userdefinedFile").value = document.getElementById("file").value;
}