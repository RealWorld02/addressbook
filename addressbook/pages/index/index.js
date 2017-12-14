//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
   
  },
  usernameInput:function(e){
    this.setData({
      userName: e.detail.value
    })
  },
  //事件处理函数
  bindtest: function () {
    var that = this; // 这个地方非常重要，重置data{}里数据时候setData方法的this应为以及函数的this, 如果在下方的sucess直接写this就变成了wx.request()的this了
    wx.request({
      url: 'http://localhost:8080/addressbook/sample/',
      data: {
        username: that.data.userName
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        that.setData({
           userID: res.data,
        })
      },
      fail: function (res) {
        that.setData({
          userID: '***fail***',
        })
      }
    })
  },

})
