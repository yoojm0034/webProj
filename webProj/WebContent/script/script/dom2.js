var formTag = document.createElement('form');
formTag.setAttribute('action', 'login.jsp');
formTag.setAttribute('method', 'get');
var id = document.createTextNode('id:');
var passwd = document.createTextNode('password:');

var inputId = document.createElement('input');
inputId.setAttribute('type', 'text');
inputId.setAttribute('name', 'id');

var inputPasswd = document.createElement('input');
inputPasswd.setAttribute('type', 'password');
inputPasswd.setAttribute('name', 'passwd');

var send = document.createElement('input');
send.setAttribute('type', 'submit');
send.setAttribute('value', 'send');

var cancel = document.createElement('input');
cancel.setAttribute('type', 'reset');
cancel.setAttribute('value', 'cancel');


formTag.appendChild(id);
formTag.appendChild(inputId);
formTag.appendChild(passwd);
formTag.appendChild(inputPasswd);
formTag.appendChild(send);
formTag.appendChild(cancel);

show.appendChild(formTag);