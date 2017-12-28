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
        that.data.addressData = lb
        wx.request({ // 百度地图API，将微信获得的经纬度传给百度，获得城市等信息
          url: 'https://api.map.baidu.com/geocoder/v2/?ak=4dS3gce88OcGLYVi40TuGi3Q2pEDVh14&location=' + lb.latitude + ',' + lb.longitude + '&output=json&coordtype=wgs84ll',
          data: {},
          header: {
            'Content-Type': 'application/json'
          },
          success: function (res) {
            console.log(res.data.result.addressComponent.city);
            console.log("555" + lb.name + "*" + lb.address + "*" + lb.latitude + "*" + lb.longitude)
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