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

// Should send "...posts/comment"
function submitComment(e) {

}

//Send POST request to /posts/upload.
// Requires Request Params "file" (multipart file), "author" (string)
// TODO: filename should be currentUser + _ + post number (of current user's total posts)
function submitComment(e) {
	var formData = new FormData();
	canvas.toBlob(
		async function(blob) {
			formData.append('file', blob, `${currentUser}_COMMENT_0.png`);
			
			await fetch(`http://localhost:8081/posts/comment?author=${currentUser}`, {
				method: 'POST',
				body: formData
			})
			.then(response => response)
			.then(data => console.log(data))
			.then(location.reload());
		}
	);
	
	console.log("Comment submitted.");
    console.log(e.value);
}