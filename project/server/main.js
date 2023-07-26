const express = require('express')
const app = express()
const path = require('path')
const port = 3000
var bodyParser = require('body-parser')
app.use(bodyParser.urlencoded({ extended: false }))
// const cars = require('./cars.json')

let cars = [{"id":152,"model":"Honda","color":"Grey","price":20},{"id":155,"model":"Mercedes","color":"Red","price":40},{"id":153,"model":"Jaguar","color":"Black","price":50},{"id":159,"model":"Honda","color":"White","price":25},{"id":151,"model":"AM","color":"Grey","price":52}]

const staticpath = path.join(__dirname, '../client')
app.use(express.static(staticpath))

app.get('/',(req,res)=>{
  res.sendFile(path.join(__dirname,'client\\index.html'))
})
app.get('/cars',(req,res)=>{
  cars = Array.from(new Set(cars))
  res.send(cars)
})
app.post('/cars',(req,res)=>{
  let new_car ={id:parseInt(req.body.id),model:req.body.model,color:req.body.color,price:parseFloat(req.body.price)}
  let flag = cars.some(car=>car.id === new_car.id)
  if(!flag) cars.push(new_car)
  res.send(cars)
})

app.post('/cars/modify',(req,res)=>{
  const action = req.body.action
  const id = parseInt(req.body.id)
  if(action==='delete'){
    let i = cars.findIndex((car)=>car.id == id)
    if(i!=-1){
      cars.splice(i,1)
    }
  }
  if(action==='edit'){
    const model = req.body.model
    const color = req.body.color
    const price = parseInt(req.body.price)
    let i = cars.findIndex((car)=>car.id == id)
    if(i!=-1){
      cars[i].model = model
      cars[i].color = color
      cars[i].price = price
    }
  }
  res.send(cars)
})


app.listen(port)