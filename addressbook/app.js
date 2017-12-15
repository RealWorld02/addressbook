//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    wx.checkSession({
      success: function () {
        //session 未过期，并且在本生命周期一直有效
      },
      fail: function () {
        //登录态过期
        wx.login() //重新登录
      }
    }),
    
      //调用登录接口，获取 code
      wx.login({
        success: function (res) {
          wx.getSetting({
            success(setRes) {
              // 判断是否已授权
              if (!setRes.authSetting['scope.userInfo']) {
                
                // 授权访问
                wx.authorize({
                  
                  scope: 'scope.userInfo',
                  success() {
                    
                    //获取用户信息
                    wx.getUserInfo({
                      lang: "zh_CN",
                      success: function (userRes) {
                        //发起网络请求
                        wx.request({
                          url: 'http://172.22.126.3:8080/addressbook/wxlogin',
                          data: {
                            code: res.code,
                            encryptedData: userRes.encryptedData,
                            iv: userRes.iv
                          },
                          header: {
                            "Content-Type": "application/x-www-form-urlencoded"  
                          },
                          method: 'GET',
                          //服务端的回掉
                          success: function (result) {
                            wx.setStorageSync("openId", result.data);
                          }
                        })
                      }
                    })
                    
                  }
                 
                })
              } else {
                //获取用户信息
               
                wx.getUserInfo({
                  lang: "zh_CN",
                  success: function (userRes) {
                    //发起网络请求
                    wx.request({
                      url: 'http://172.22.126.3:8080/addressbook/wxlogin',
                      data: {
                        code: res.code,
                        encryptedData: userRes.encryptedData,
                        iv: userRes.iv
                      },
                      header: {
                        "Content-Type": "application/x-www-form-urlencoded"  
                      },
                      method: 'GET',
                      success: function (result) {
                        wx.setStorageSync("openId", result.data);
                      }
                    })
                  }
                })
              }
            }
          })
        }
      })
  },
  globalData: {
    userInfo: null
  }
})