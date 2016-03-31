<!DOCTYPE html>
    <html>
        <head>
            <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.14/angular.min.js"></script>
            <script type="text/javascript" src="https://raw.githubusercontent.com/abdmob/x2js/master/xml2json.min.js" charset="UTF-8"></script>
            <meta charset="utf-8">
            <title>Parsing XML in ajgularJS</title> 
        </head>
        <body ng-app="todosApp">
        <h2>Parsing XML data with AngularJS</h2>
        <input type="text" ng-model="search" class="search-query" placeholder="Search">
        <div ng-controller="todos"><ol><li ng-repeat="todo in todos | filter:search">{{todo.name}}</div>
        <script>
        var todoApp = angular.module('todosApp',[]);
        
        todoApp.factory('todoFactory',function($http){
          var factory = [];
          
          factory.getTodos = function(){
            return $http.get("http://cdn.rawgit.com/motyar/bcf1d2b36e8777fd77d6/raw/bfa8bc0d2d7990fdb910927815a40b572c0c1078/out.xml");
          }
        
            return factory;
        });
        
        todoApp.controller('todos',function($scope,todoFactory){
          
          $scope.todos = [];
          loadTodos();
          
          function loadTodos(){
            var x2js = new X2JS();
            todoFactory.getTodos().success(function(data){
                courses  = x2js.xml_str2json(data);
                console.log(courses.books.course);
                $scope.todos =courses.books.course;
            });
            }
        });
</script> 
</body>
</html>