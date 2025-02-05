document.addEventListener("DOMContentLoaded", function () {
    let table = document.getElementById("jobTable");

    table.addEventListener("click", function (event) {
        let target = event.target;
        if (target.tagName === "TD") {
            alert("선택한 직무 ID: " + target.parentElement.cells[0].innerText);
        }
    });
});
