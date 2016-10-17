var index = $('#index');
var indexObject = JSON.parse(index.text());
index.text('');

var indexDOM = '<ul>';

for (var i = 0; i < indexObject.length; i++) {
  item = indexObject[i];
  if(Array.isArray(item)) {
    indexDOM += '<ul>';
    item.forEach(function(subItem) {
      a = '<a href="/'+indexObject[i-1]+'/'+subItem+'">'+subItem+'</a>';
      indexDOM += '<li>' + a + '</li>';
    });
    indexDOM += '</ul>';
  } else {
    a = '<a href="/'+item+'">'+item+'</a>';
    indexDOM += '<li>' + a + '</li>';
  }
}
indexDOM += '</ul>';


index.html(indexDOM);