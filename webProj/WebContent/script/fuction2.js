//function2.js

var names = ['구자혁','유정모','소국진','전형민'];

function makeList(nameAry) {
    var ulTag = '<ul>';
    for(str of nameAry) { //for(String str : strAry)
        ulTag += '<li>' + str + '</li>';
    }
    ulTag += '</ul>';
    return ulTag;
}

var show = makeList(names);
console.log(show);
document.write(show);