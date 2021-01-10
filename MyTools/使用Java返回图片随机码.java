/**图片回传
 使用该url可以直接访问图片
 */
@RequestMapping(value = "/zhfw/system/getPic", method = RequestMethod.GET)
public void getPic(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//如何让浏览器3秒自动刷新一次;
		response.setHeader("refresh","3");
		//在内存中创建一个图片
		BufferedImage image = new
		BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
		//得到图片
		Graphics2D g = (Graphics2D) image.getGraphics(); //笔
		//设置图片的背景颜色
		g.setColor(Color.white);
		g.fillRect(0,0,80,20);
		//给图片写数据
		g.setColor(Color.BLUE);
		g.setFont(new Font(null,Font.BOLD,20));
		g.drawString("123456",0,20);
		//告诉浏览器，这个请求用图片的方式打开
		response.setContentType("image/jpeg");
		//网站存在缓存，不让浏览器缓存
		response.setDateHeader("expires",-1);
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Pragma","no-cache");
		//把图片写给浏览器
		ImageIO.write(image,"jpg", response.getOutputStream());
		}