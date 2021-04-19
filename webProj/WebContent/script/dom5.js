var buttons = document.getElementsByTagName('button');
buttons[0].setAttribute('onclick', 'showTable()');


var p1 = {
    name: '성진아',
    score: 80
};
var p2 = {
    name: '김수민',
    score: 83
};
var p3 = {
    name: '장재우',
    score: 85
};


var persons = [p1, p2, p3];

function showTable() {
    var tableTag = document.createElement('table');
    tableTag.setAttribute('border', '1');
    for (var p of persons) {
        var tr = document.createElement('tr');
        for (var field in p) {
            var td1 = document.createElement('td');
            td1.innerHTML = p[field];
            tr.appendChild(td1);
        }
        tableTag.appendChild(tr);
    }
    var show = document.getElementById('show');
    show.appendChild(tableTag);
}
// for(var p of persons) { //배열의 개수만큼 반복
//     for(var field in p) { //필드의 개수만큼 반복

//     }
// }