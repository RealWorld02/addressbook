//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
 
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
            that.saveAddress(lb, res.data.result);
          },
          fail: function () {
          },
          complete: function () {
          }
        })
      },
      cancel: function (lb) {
        wx.navigateBack({
          delta: 1
        })
      },
      fail: function (lb) {
      }
    })
  },

  saveAddress: function (lb, result) {
    var that = this
    wx.request({
      url: 'http://172.22.126.3:8080/addressbook/saveAddress',
      data: {
        openId: wx.getStorageSync('openId'),
        addCountry: result.addressComponent.country,
        addProvince: result.addressComponent.province,
        addCity: result.addressComponent.city,
        addDistrict: result.addressComponent.district,
        latitude: result.location.lat,
        longitude: result.location.lng,
        detailAddName: lb.name,
        detailAddress: lb.address
      },
      header: {
        'Content-Type': 'application/json'
      },
      method: 'GET',
      success: function (result) {
        wx.reLaunch({
          url: '../user/user'
        })
        console.log(result.data)
      }
    })
  }
})