function Car(id,model,color,price){
  this.id = id
  this.model = model
  this.color = color
  this.price = price
}

// VIEW/LOAD DATA
$(document).ready(loadData)

function loadData(){
  $.ajax({
    url:'/cars',
    method: 'GET',
    dataType: 'json',
    success: data=>{
      const carlist = $('#data')
      count = data.length
      if(data.length>0){
        carlist.html('<tr><th>Id</th><th>Model</th><th>Color</th><th>Price</th><th>Modify</th></tr>')
        $('#modify').css('opacity',1)
        $('#modify').css("pointer-events", 'unset')
      }
      else{
        carlist.html('')
        $('#modify').css('opacity',0.5)
        $('#modify').css("pointer-events", 'none')
      }
      data = data.sort((a,b)=>a.id-b.id)
      for(let i=0;i<data.length;i++){
        const caritem = `<tr><td>${data[i].id}</td><td>${data[i].model}</td><td>${data[i].color}</td><td>${data[i].price}</td><td><button class="editbtn" data-id="${data[i].id}" data-model="${data[i].model}" data-color="${data[i].color}" data-price="${data[i].price}">Edit</button><button class="deletebtn" data-id="${data[i].id}" data-model="${data[i].model}" data-color="${data[i].color}" data-price="${data[i].price}">Delete</button></td></tr>`
        carlist.append(caritem)
      }
      $('#data tr > *:nth-child(5)').hide()
      $('#appttl').text('Operation: View')
      $('#modify').text('Modify entry')
      $('#addformpar').hide()
      $('#editformpar').hide()
      return count
    },
    error:err=> console.log(err)
  })
}

$('#modify').click(function(){
  if($('#data tr > *:nth-child(5)').css('display') === 'none'){
    $('#data tr > *:nth-child(5)').show()
    $(this).text('Cancel')
    $('#appttl').text('Operation: Modify')
  }
  else{
    $('#data tr > *:nth-child(5)').hide()
    $(this).text('Modify entry')
    $('#appttl').text('Operation: View')
  }
})
$('#add').click(function(){
  $('#addformpar').show()
  $('#data tr > *:nth-child(5)').hide()
})



// ADD NEW ENTRY

$('#ccolor').on('input',function(){
  let flag = CSS.supports('color',$('#ccolor').val())
  if(flag)
    $('#swatch1').css('background',$('#ccolor').val())
  else
    $('#swatch1').css('background','url(./nocolor.jpg) no-repeat center center/cover')
})

function exAddForm(){
  $('#warning').text('')
  const values ={
    id : $('#cid').val(),
    model : $('#cmodel').val(),
    color : $('#ccolor').val(),
    price : $('#cprice').val()
  }
  if(values.id!='' && values.model!='' && values.color!='' && values.price!=''){
      if(CSS.supports('color',values.color)){
        $('#warning').css('transform','translateY(-100%)')
        $('#cid').val('')
        $('#cmodel').val('')
        $('#ccolor').val('')
        $('#cprice').val('')
        $('#swatch1').css('background','url(./nocolor.jpg) no-repeat center center/cover')
        return new Car(parseInt(values.id),values.model,values.color,parseInt(values.price))
      }
      else{
        $('#warning').css('transform','translateY(10px)')
        setTimeout(()=>{
          $('#warning').css('transform','translateY(0)')
        },100)
        $('#warning').text('Enter a valid color')
      }
  }
  else{
    $('#warning').css('transform','translateY(10px)')
    setTimeout(()=>{
      $('#warning').css('transform','translateY(0)')
    },100)
    if(values.id=='') $('#warning').append('Car id cannot be empty. ')
    if(values.model=='') $('#warning').append('Model cannot be empty. ')
    if(values.color=='') $('#warning').append('Color cannot be empty. ')
    if(values.price=='') $('#warning').append('Price cannot be empty.')
  }
  return null
}

$('#addform').on('submit',function(e){
  e.preventDefault()

  let c = exAddForm()
  if(c!=null && $('#addform').css('display')!='none'){
    $.ajax({
      url:'/cars/add',
      method: 'POST',
      dataType: 'json',
      data:c,
      success: data=>{
        let count = $('#data').children().length-1
        loadData()
        if(count===data.length){
          $('#warning').css('transform','translateY(10px)')
          setTimeout(()=>{
            $('#warning').css('transform','translateY(0)')
            setTimeout(()=>{
              $('#warning').css('transform','translateY(-100%)')
            },2000)
          },100)
          $('#warning').text('Entry with same id already exist')
        }
      },
      error: ()=>console.log('Could not add new entry')
    })
  }
})

$('#sub-cancel').click(function(){
    $('#warning').css('transform','translateY(-100%)')
    $('#addformpar').hide()
})

// MODIFY ENTRY - EDIT
$('#uccolor').on('input',function(){
  let flag = CSS.supports('color',$('#uccolor').val())
  if(flag)
    $('#swatch2').css('background',$('#uccolor').val())
  else
    $('#swatch2').css('background','url(./nocolor.jpg) no-repeat center center/cover')
})

function exUpdateForm(){
  $('#warning').text('')
  const values ={
    id : $('#ucid').val(),
    model : $('#ucmodel').val(),
    color : $('#uccolor').val(),
    price : $('#ucprice').val()
  }
  if(values.id!='' && values.model!='' && values.color!='' && values.price!=''){
      if(CSS.supports('color',values.color)){
        $('#warning').css('transform','translateY(-100%)')
        return new Car(parseInt(values.id),values.model,values.color,parseInt(values.price))
      }
      else{
        $('#warning').css('transform','translateY(10px)')
        setTimeout(()=>{
          $('#warning').css('transform','translateY(0)')
        },100)
        $('#warning').text('Enter a valid color')
      }
  }
  else{
    $('#warning').css('transform','translateY(10px)')
    setTimeout(()=>{
      $('#warning').css('transform','translateY(0)')
    },100)
    if(values.id=='') $('#warning').append('Update Car id cannot be empty. ')
    if(values.model=='') $('#warning').append('Update Model cannot be empty. ')
    if(values.color=='') $('#warning').append('Update Color cannot be empty. ')
    if(values.price=='') $('#warning').append('Update Price cannot be empty.')
  }
  return null
}


$(document).on('click', '.editbtn', function(){
  $('#ucid').val($(this).data('id'))
  $('#ucmodel').val($(this).data('model'))
  $('#uccolor').val($(this).data('color'))
  $('#ucprice').val($(this).data('price'))
  $('#swatch2').css('background',$(this).data('color'))
  $('#editformpar').show()
  
  $('#editform').on('submit',function(e){
    e.preventDefault()
    c = exUpdateForm()
    if(c!=null && $('#editform').css('display')!='none'){
      $.ajax({
        url:'/cars/modify',
        method: 'POST',
        dataType: 'json',
        data:{action:'edit',id:c.id,model:c.model,color:c.color,price:c.price},
        success: ()=>{
          loadData()
          $('#warning').css('transform','translateY(10px)')
          setTimeout(()=>{
            $('#warning').css('transform','translateY(0)')
            setTimeout(()=>{
              $('#warning').css('transform','translateY(-100%)')
              $('#warning').css('background','#e02a2a')
            },2000)
          },100)
          $('#warning').text('Update successful')
          $('#warning').css('background','#3bd080')
        },
        error: ()=>console.log('Could not update entry')
      })
    }
  })
})

$('#sub-ucancel').click(function(){
  $('#warning').css('transform','translateY(-100%)')
  $('#editformpar').hide()
})

// MODIFY ENTRY - DELETE
$(document).on('click', '.deletebtn', function(){
  let id = $(this).data('id')
    
  $.ajax({
    url:'/cars/modify',
    method: 'POST',
    dataType: 'json',
    data:{action:'delete',id:id},
    success: data=>{
      if(data!=null){
        loadData()
        $('#warning').css('transform','translateY(10px)')
        setTimeout(()=>{
          $('#warning').css('transform','translateY(0)')
          setTimeout(()=>{
            $('#warning').css('transform','translateY(-100%)')
            $('#warning').css('background','#e02a2a')
          },2000)
        },100)
        $('#warning').text('Delete successful')
        $('#warning').css('background','#3bd080')
      }
      else{
        $('#warning').css('transform','translateY(10px)')
        setTimeout(()=>{
          $('#warning').css('transform','translateY(0)')
          setTimeout(()=>{
            $('#warning').css('transform','translateY(-100%)')
          },2000)
        },100)
        $('#warning').text('Couldn\'t delete entry')
      }
    },
    error: ()=>console.log('Could\'t delete entry')
  }) 
})