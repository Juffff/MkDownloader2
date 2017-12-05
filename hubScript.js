const a = [];
const b = [];
const c = [];
const arr = document.querySelectorAll('td.sorting_1 > a');
for (let i = 0; i < arr.length; i++) {
    a.push(arr[i].getAttribute('href'));
    b.push(arr[i].innerHTML);
}
for (let i = 0; i < a.length; i++) {
    c.push(a[i].split('product/')[1].split('/')[0] + "#" + b[i]);
}

console.log('a_length', a.length);
console.log('b_length', b.length);
console.log('c_length', c.length);

const table = document.createElement('table');
for(let i=0; i< a.length; i++){
    const tr = document.createElement('tr');
    const td1  = document.createElement('td');
    td1.innerText  = a[i];
    const td2  = document.createElement('td');
    td2.innerText  = b[i];
    const td3  = document.createElement('td');
    td3.innerText  = c[i];
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    table.appendChild(tr);
}
var body = document.body;
while (body.firstChild) {
    body.removeChild(body.firstChild);
}
body.appendChild(table);



const hrefArr = [];
const aArr = document.querySelectorAll('td.text-center.sorting_1 > a:nth-child(1)');
for (let i = 0; i < aArr.length; i++) {
    // hrefArr.push('https://hub.modnakasta.ua' + aArr[i].getAttribute('href'));
    hrefArr.push(aArr[i].getAttribute('href'));
}

/*fetch(hrefArr[1])
 .then(function (response) {
 console.log(response.headers.get('Content-Type')); // application/json; charset=utf-8
 console.log(response.status); // 200
 return response.text();
 })
 .then(function (data) {
 console.log(data);
 })
 .catch(alert);*//**
 * Created by Juff on 06.12.17.
 */
