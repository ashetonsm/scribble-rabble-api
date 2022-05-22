const newCanvas = document.createElement("canvas");
newCanvas.width = 500;
newCanvas.height = 250;
newCanvas.setAttribute('id', "comment-canvas");

function showCommentArea(e) {
	console.log(e.nextElementSibling.children);

	// If there is no canvas for this comment area, create one
	if (e.nextElementSibling.children[0].children.length == 0) {
		e.nextElementSibling.children[0].append(newCanvas);
	}


    // If display.none, change to block
    if (e.nextElementSibling.style.display == "none") {
        e.nextElementSibling.style.display = "block";
    } else {
    // if display block, change to none
		document.getElementById("comment-canvas").remove();
        e.nextElementSibling.style.display = "none";
    }
    console.log("Comment area toggled.");
}

//Send POST request to /posts/comment.
function submitComment(e) {
	console.log(e.value);

	const canvas = document.getElementById('comment-canvas');
	var formData = new FormData();
	canvas.toBlob(
		async function(blob) {
			formData.append('file', blob, `${currentUser}_COMMENT_0.png`);

			await fetch(`http://localhost:8081/posts/comment?author=${currentUser}&postID=${e.value}`, {
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
