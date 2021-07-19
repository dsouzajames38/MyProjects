	var _mybankAPIgateway = angular.module('_mybankAPIgateway', []); 

	_mybankAPIgateway.controller('_mybankAPIgatewayController', function ($scope, $http) {
	
	
	$scope.account = {};
	$scope.data ="";
	$scope.showtransactionhistory = false;
	$scope.showaccountbalance = false;

		//Generic function to call any api url 
		$scope.account.callapi = function (url)
		{
			alert("calling api " + url);
			
			$scope.msg = "calling api ..";
			$scope.statusval = "";
			$scope.statustext = "";
			
			$http({
				method: 'POST',
				url: url,
				data: $scope.data,
				dataType: 'text',
				headers: {'Content-Type':'text/plain;charset=UTF-8' }
			}).then(function successCallback(response) {
				// this callback will be called asynchronously
				// when the response is available
				$scope.msg = "Post Data Submitted Successfully!";
				$scope.data.queryresponse = response.data;
				alert(response.data);
				
			  }, function errorCallback(response) {
				$scope.msg = "Service not Exists: " + response.data;
				$scope.statusval = response.status;
				$scope.statustext = response.statusText;
				$scope.headers = response.headers();
			  });

		}
			
		
		//Create an Account in Wallet
		$scope.account.createaccount = function () 
		{
			$scope.showtransactionhistory = false;
			$scope.showaccountbalance = false;
	
			//set the data before calling api
			$scope.data='{"accountId":"' + $scope.accountId +'"}';
			alert("create account function called for account id=" + $scope.accountId);
			alert("sending as body ... " + $scope.data);
			
			$scope.account.callapi('https://yq0xr3ilce.execute-api.ap-southeast-1.amazonaws.com/prod/');	
		}; 
		
		//Add funds in Wallet
		$scope.account.addfunds = function () 
		{
			$scope.showtransactionhistory = false;
			$scope.showaccountbalance = true;
	
			//$scope.amount = 10;
			//set the data before calling api
			//`{ "accountId": "<accountId>", "amount": 	<number> }`
			$scope.data='{' + '"accountId":"' + $scope.accountId +'"' + "," + '"amount": ' + $scope.amount + '}';
			alert("create account function called for account id=" + $scope.accountId);
			alert("sending as body ... " + $scope.data);
			
			$scope.account.callapi('https://5k3off8ul6.execute-api.ap-southeast-1.amazonaws.com/prod/');	
		};
		
		//Withdraw funds from Wallet
		$scope.account.withdrawfunds = function () 
		{
			
			$scope.showtransactionhistory = false;
			$scope.showaccountbalance = false;
	
	
			//set the data before calling api
			//`{ "accountId": "<accountId>", "amount": 	<number> }`
			$scope.data='{' + '"accountId":"' + $scope.accountId +'"' + "," + '"amount": ' + $scope.amount + '}';
			alert("create account function called for account id=" + $scope.accountId);
			alert("sending as body ... " + $scope.data);
			
			$scope.account.callapi('https://otbkwc8l8d.execute-api.ap-southeast-1.amazonaws.com/prod/');	
		};
		
		//Get Fund Balance in Wallet
		$scope.account.getfunds = function () 
		{
			$scope.showtransactionhistory = false;
			$scope.showaccountbalance = true;
	
	
			//set the data before calling api
			$scope.data='{"accountId":"' + $scope.accountId +'"}';
			alert("create account function called for account id=" + $scope.accountId);
			alert("sending as body ... " + $scope.data);
			
			$scope.account.callapi('https://6k41tz971a.execute-api.ap-southeast-1.amazonaws.com/prod/');	
		};
		
		//Get Transaction History in Wallet
		$scope.account.gettransactionhistory = function () 
		{
			$scope.showtransactionhistory = true;
			$scope.showaccountbalance = false;
	
			//set the data before calling api
			$scope.data='{"accountId":"' + $scope.accountId +'"}';
			alert("create account function called for account id=" + $scope.accountId);
			alert("sending as body ... " + $scope.data);
			
			$scope.account.callapi('https://rh71jloqs6.execute-api.ap-southeast-1.amazonaws.com/prod/');	
		};

}); /*mainApp.dataController*/