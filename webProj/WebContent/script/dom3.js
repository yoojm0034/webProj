var names = [];
names[0] = '유정모';
names.push('구자혁'); //마지막위치 배열 추가
names.push('석정원'); 
names.pop();    //마지막 위치 배열 삭제
delete names[0]; //해당 위치에 정보만 삭제 

names.shift(); //첫번째 위치부터 삭제
names.unshift('김이담'); //첫번째위치 추가
names.push('공도현');
names.push('소국진');
names.push('전형민');

var returnVal = names.map(function (val, idx, ary) {
   var person = {};
   person.names = val;
   person.num = idx;

    return person;

});

var returnFil = returnVal.filter(function (val, idx, ary) {
    return idx % 2 == 0;
});

// names.forEach(function(val, idx, ary) {
//     console.log(`names 요소: ${val}, ${idx}, ${ary}`);
// });



console.log(returnVal);
console.log('==========')
console.log(returnFil); 