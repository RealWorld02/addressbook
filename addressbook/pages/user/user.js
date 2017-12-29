//获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {}
  },

  onLoad: function () {
    this.getUserInfo()
  },
  setMap:function(){
    wx.navigateTo({
      url: '../userMap/userMap'
    })
  },
  getUserInfo:function(){
    var that = this
    wx.request({
      url: 'http://172.22.126.3:8080/addressbook/getUserInfo',
      data: {
        openId: wx.getStorageSync('openId'),
      },
      header: {
        'Content-Type': 'application/json'
      },
      method: 'GET',
      success: function (result) {
        that.setData({
          userInfo:result.data
        })
        console.log(result.data)
      }
    })
  }
})
