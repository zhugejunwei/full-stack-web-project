var uniqueId = function() {
  return 'id-' + Math.random().toString(36).substr(2, 16);
};

var database = {
  colors: [
    { id: uniqueId(), color: 'blue' },
    { id: uniqueId(), color: 'green'}
  ]
}
var server = sinon.fakeServer.create();
server.autoRespond = true
server.autoRespondAfter = 400

server.respondWith("GET", "/api/colors",
  [200, { "Content-Type": "application/json" }, JSON.stringify(database.colors)]);

server.respondWith('POST', '/api/colors', function (request){
  color = JSON.parse(request.requestBody)
  color.id = uniqueId()
  database.colors.push(color)
  request.respond(
    200, {'Content-Type': 'application/json'}, JSON.stringify(color) )
})
