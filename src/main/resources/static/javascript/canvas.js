const canvas = document.getElementById('canvas');
const submitButton = document.getElementById('submitPost');
const context = canvas.getContext('2d');
context.fillStyle = 'black';
let isDrawing = false;
let startX;
let startY;

const draw = (e) => {
    if(!isDrawing) {return;}
    context.lineWidth = 2;
    context.lineTo(e.clientX - canvas.offsetLeft, e.clientY - canvas.offsetTop);
    context.stroke();
}

//Send POST request to /posts/upload.
// Requires Request Params "file" (multipart file), "author" (string)
function submitPost() {
	console.log("clicked");
	var formData = new FormData();
	canvas.toBlob(
		async function(blob) {
			formData.append('file', blob, 'filename.png');
			
			await fetch(`http://localhost:8081/posts/upload?author=testAuthor`, {
				method: 'POST',
				body: formData
			})
			.then(response => response)
			.then(data => console.log(data));
			
		}
	);
}

canvas.addEventListener('mousedown', (e) => {
    isDrawing = true;
    console.log("Started a stroke.");
    context.fillRect(e.clientX - canvas.offsetLeft, e.clientY - canvas.offsetTop, 2, 2);

});

canvas.addEventListener('mouseup', (e) => {
    console.log("Ended a stroke.");
    isDrawing = false;
    context.stroke();
    context.beginPath();
});

canvas.addEventListener('mousemove', draw);
submitButton.addEventListener('click', submitPost);