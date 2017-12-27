//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    city: ''
  },
  onLoad: function (options) {
    this.loadInfo();
  },
  loadInfo: function () {
    var that = this
    wx.chooseLocation({   // 利用微信选择位置API，获得经纬度信息  
      success: function (lb) {
        console.log("地理位置")
        console.log("111111"+lb)
        that.data.addressData = lb
        wx.request({ // 百度地图API，将微信获得的经纬度传给百度，获得城市等信息
          url: 'https://api.map.baidu.com/geocoder/v2/?ak=4dS3gce88OcGLYVi40TuGi3Q2pEDVh14&location=' + lb.latitude + ',' + lb.longitude + '&output=json&coordtype=wgs84ll',
          data: {},
          header: {
            'Content-Type': 'application/json'
          },
          success: function (res) {
            console.log("2222"+res.data.result);
            console.log("333333"+res.data.result.addressComponent.city + res.data.result.addressComponent.district);
            that.setData({
              addressAll: res.data.result.addressComponent.city + res.data.result.addressComponent.district + "·" + lb.name})
            console.log("4444" + res.data.result.addressComponent.city + res.data.result.addressComponent.district + "·" + lb.name+"*"+lb.address)
          },
          fail: function () {
            // fail
          },
          complete: function () {
            // complete
          }
        })
      },
      cancel: function (lb) {
      },
      fail: function (lb) {
        console.log(lb)
      }
    })
  }
})