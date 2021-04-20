function createTitle() {
	var tr = document.createElement('tr');

	for (var i = 0; i < arguments.length; i++) {
		var td = document.createElement('td');
		td.innerHTML = arguments[i];
		tr.appendChild(td)
	}
	document.getElementById('tbl').appendChild(tr)
}

function createElement() {
	for (var p of persons) {
		var tr = document.createElement('tr');
		tr.setAttribute('id', p.id);
		tr.onmouseover = mouseOverFnc;
		tr.onmouseout = mouseOutFnc;
		for (var field in p) {
			if (field == 'id') {
				var td = document.createElement('td');
				td.onclick = modifyFnc;
				td.innerHTML = p[field];
				tr.appendChild(td);
			} else if (field == 'name') {
				var td = document.createElement('td');
				var link = document.createElement('a');
				link.setAttribute('href', 'dom.jsp?name=' + p.name + '&id=' + p.id + '&score=' + p.score + '&gender=' + p.gender);
	 			link.innerHTML = p.name;

				td.appendChild(link);
				tr.appendChild(td);
			} else {
				var td = document.createElement('td');
				td.innerHTML = p[field];
				tr.appendChild(td);
			}

		}
		var td = document.createElement('td');
		var btns = document.createElement('button');
		btns.innerHTML = '삭제';
		td.appendChild(btns)
		btns.onclick = deleteRow;

		tr.appendChild(td);
		document.getElementById('tbl').appendChild(tr);
	}
}

function mouseOverFnc() {
	this.style.backgroundColor = 'yellow';
}

function mouseOutFnc() {
	this.style.backgroundColor = '';
}

function deleteRow() {
	this.parentNode.parentNode.remove();
}

function modifyFnc() {
	var idVal = this.innerHTML;
	var nameVal = this.previousSibling.innerHTML;
	var scoreVal = this.nextSibling.innerHTML;
	var genVal = this.parentNode.childNodes[3].innerHTML;


	document.getElementById('name').value = nameVal;
	document.getElementById('id').value = idVal;
	document.getElementById('score').value = scoreVal;
	var genders = document.querySelectorAll('input[name="gender"]');
	for (var i = 0; i < genders.length; i++) {
		if (genders[i].value == genVal) {
			genders[i].checked = true;
		}
	}
}

function saveBtnFnc() {
	var iName = document.getElementById('name');
	var iId = document.querySelector('input[name="id"]');
	var iScore = document.getElementsByTagName('input')[2];
	var iGender = document.querySelector('input[name="gender"]:checked');


	var tr = document.createElement('tr');
	tr.onmouseover = mouseOverFnc;
	tr.onmouseout = mouseOutFnc;
	var td = document.createElement('td');
	td.innerHTML = iName.value;
	tr.appendChild(td);
	var td = document.createElement('td');
	td.innerHTML = iId.value;
	tr.appendChild(td);
	var td = document.createElement('td');
	td.innerHTML = iScore.value;
	tr.appendChild(td);
	var td = document.createElement('td');
	td.innerHTML = iGender.value;
	tr.appendChild(td);
	var td = document.createElement('td');
	var btns = document.createElement('button');
	btns.innerHTML = '삭제';
	td.appendChild(btns)
	btns.onclick = deleteRow;
	tr.appendChild(td);
	document.getElementById('tbl').appendChild(tr);
}
function modifyBtnFnc() {

	var id = document.getElementById('id').value;
	console.log(id);
	var targetTr = document.getElementById(id)
	console.log(targetTr);
	targetTr.children[0].innerHTML = document.getElementById('name').value;
	targetTr.children[2].innerHTML = document.getElementById('score').value;
	targetTr.children[3].innerHTML = document.querySelector('input[name="gender"]:checked').value;
}