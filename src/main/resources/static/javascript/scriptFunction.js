

function validateBtn(){
	var currentPageNumber = document.getElementById('currentPage');
	var totalPageNumber = document.getElementById('totalOfPages');
	
	var btnBack = document.getElementById('btnBack');
	var btnNext = document.getElementById('btnNext');
	
	if(currentPageNumber.textContent == '0'){
		btnBack.disabled = true;
	}else{
		btnBack.disabled = false;
	}
	
	if(currentPageNumber.textContent == totalPageNumber.textContent ){
		btnNext.disabled = true;
	}else{
		btnNext.disabled = false;
	}
}

function clearTable(){
	 var table = document.getElementById('tableData');
	 const tableSize   = table.rows.length-1;
	 
	    for(index=0;index < tableSize ;index++){
	    	table.deleteRow(1);
	    }
}



function managePageData( pageMode){
	
	var adressDara ='http://'+document.URL.split('/')[2]+'/data/getDataPerson/'+pageMode;
	var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		     personArray	 = JSON.parse(this.responseText)
		     personArray.forEach(addPersontoTable);
		    }
		  };
	         // prencher os dados da api na tabela nesta pagina
		  xhttp.open("GET", adressDara, false);
		  xhttp.send();
}

 function requestDonwloadFile(fileId){
	 
		var adressDara ='http://'+document.URL.split('/')[2]+'/files/getFile?id='+fileId;
	
		var a = document.createElement("a");
		  a.href = adressDara;
		  a.setAttribute("download", "file"+fileId);
		  a.click();
	 // call the api for donload the file
	 
 }

function addPersontoTable(item,index){
	//alert('funcao');
	var table = document.getElementById("tableData");
	  var row = table.insertRow(index+1);
	  var colunm1 = row.insertCell(0);
	  var colunm2 = row.insertCell(1);
	  var colunm3 = row.insertCell(2);
	  var colunm4 = row.insertCell(3);
	  var colunm5 = row.insertCell(4);
	  var colunm6 = row.insertCell(5);
	  var colunm7 = row.insertCell(6);
	  
	  colunm1.innerHTML = item.id;
	  colunm2.innerHTML = item.firstName;
	  colunm3.innerHTML = item.lastName;
	  colunm4.innerHTML = item.age;
	  colunm5.innerHTML = item.street;
	  colunm6.innerHTML =  item.emaill;
	  var botao ='<button class="btn btn-secondary" type="button" value="?" onclick="requestDonwloadFile(value)">Download</button>'.replace('?',item.id);
	  colunm7.innerHTML = botao;  
}

function atributeCurrentAndTotalPages( ){
	
	var currentPageNumber =  document.getElementById('currentPage');
	var totalPageNumber =  document.getElementById('totalOfPages');
	
	var adressDataCurrent ='http://'+document.URL.split('/')[2]+'/data/getCurrentPageIndex';
	var adressDataTotal ='http://'+document.URL.split('/')[2]+'/data/getTotalOfPages';

	var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	currentPageNumber.innerText =this.responseText;
		    }
		  };
	         // prencher os dados da api na tabela nesta pagina
		  xhttp.open("GET", adressDataCurrent, false);
		  xhttp.send();
	
	  var xhttp = new XMLHttpRequest();
		 xhttp.onreadystatechange = function() {
				    if (this.readyState == 4 && this.status == 200) {
				    	totalPageNumber.innerText =this.responseText;
				    }
				  };
			         // prencher os dados da api na tabela nesta pagina
				  xhttp.open("GET", adressDataTotal, false);
				  xhttp.send();
}




function movePages(pageMode){
	clearTable();
	managePageData(pageMode);
	atributeCurrentAndTotalPages();
	validateBtn();
}


function search(){
	

	var searchValue = document.getElementById('searchId');
	searchValue = searchValue.value;
	
	if(searchValue.length == 0){
		movePages('default');
	}else{
	
	var searchAdr = 'http://'+document.URL.split('/')[2]+'/data/search?id='+searchValue;
	clearTable();
	
	 var xhttp = new XMLHttpRequest();
	 xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			    	personArray = JSON.parse( this.responseText);
			    	 personArray.forEach(addPersontoTable);
			    }
			  };
		         // prencher os dados da api na tabela nesta pagina
			  xhttp.open("GET", searchAdr, false);
			  xhttp.send();
	
	}
	
}




