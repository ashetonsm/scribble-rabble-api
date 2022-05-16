function showCommentArea(e) {
console.log(e.nextElementSibling);
    // If display.none, change to block
    if (e.nextElementSibling.style.display == "none") {
        e.nextElementSibling.style.display = "block";
    } else {
    // if display block, change to none
        e.nextElementSibling.style.display = "none";
    }
    console.log("Comment area toggled.");
}

function submitComment(e) {
    console.log("Comment submitted.");
    console.log(e.value);
}