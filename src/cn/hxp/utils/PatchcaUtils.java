package cn.hxp.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.patchca.background.BackgroundFactory;
import org.patchca.color.ColorFactory;
import org.patchca.color.RandomColorFactory;
import org.patchca.filter.ConfigurableFilterFactory;
import org.patchca.filter.library.AbstractImageOp;
import org.patchca.filter.library.WobbleImageOp;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.Captcha;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.text.renderer.TextRenderer;
import org.patchca.word.RandomWordFactory;

public class PatchcaUtils {

	private static String PATCHCA_CHARACTERS = "abcdefghkmnpqstwxyz23456789";
	
	public static String DEFAULT_VALIDATE_IMAGE_TYPE = "png";

	private static int IMAGE_WIDTH = 131;
	
	private static int IMAGE_HEIGHT = 44;
	
	private static int FONT_MAX_SIZE = 28;

	private static int FONT_MIN_SIZE = 25;
	
	private static int WORD_MAX_LENGTH = 4;

	private static int WORD_MIN_LENGTH = 4;
	
	public static String generateCptcha(OutputStream outputStream) throws IOException {
		return generateCptcha(outputStream, DEFAULT_VALIDATE_IMAGE_TYPE);
	}

	public static String generateCptcha(OutputStream outputStream, String imageType) throws IOException {
		ConfigurableCaptchaService configurableCaptchaService = new ConfigurableCaptchaService();

		// 颜色创建工厂,使用一定范围内的随机色
		ColorFactory colorFactory = new RandomColorFactory();
		configurableCaptchaService.setColorFactory(colorFactory);

		// 随机字体生成器
		RandomFontFactory fontFactory = new RandomFontFactory();
		fontFactory.setMaxSize(FONT_MAX_SIZE);
		fontFactory.setMinSize(FONT_MIN_SIZE);
		configurableCaptchaService.setFontFactory(fontFactory);

		// 随机字符生成器,去除掉容易混淆的字母和数字,如o和0等
		RandomWordFactory wordFactory = new RandomWordFactory();
		wordFactory.setCharacters(PATCHCA_CHARACTERS);
		wordFactory.setMaxLength(WORD_MAX_LENGTH);
		wordFactory.setMinLength(WORD_MIN_LENGTH);
		configurableCaptchaService.setWordFactory(wordFactory);

		// 自定义验证码图片背景
		BackgroundFactory backgroundFactory = new PachcaBackgroundFactory();
		configurableCaptchaService.setBackgroundFactory(backgroundFactory);

		// 图片滤镜设置
		ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();

		List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();
		WobbleImageOp wobbleImageOp = new WobbleImageOp();
		wobbleImageOp.setEdgeMode(AbstractImageOp.EDGE_MIRROR);
		wobbleImageOp.setxAmplitude(2.0);
		wobbleImageOp.setyAmplitude(1.0);
		filters.add(wobbleImageOp);
		filterFactory.setFilters(filters);

		configurableCaptchaService.setFilterFactory(filterFactory);

		// 文字渲染器设置
		TextRenderer textRenderer = new BestFitTextRenderer();
		textRenderer.setBottomMargin(3);
		textRenderer.setTopMargin(3);
		configurableCaptchaService.setTextRenderer(textRenderer);

		// 验证码图片的大小
		configurableCaptchaService.setWidth(IMAGE_WIDTH);
		configurableCaptchaService.setHeight(IMAGE_HEIGHT);

		Captcha captcha = configurableCaptchaService.getCaptcha();	
		BufferedImage bufferedImage = captcha.getImage();
        ImageIO.write(bufferedImage, imageType, outputStream);

		return captcha.getChallenge();
	}
	
	public static class PachcaBackgroundFactory implements BackgroundFactory {
		private Random random = new Random();

		public void fillBackground(BufferedImage image) {
			Graphics graphics = image.getGraphics();

			// 验证码图片的宽高
			int imgWidth = image.getWidth();
			int imgHeight = image.getHeight();

			// 填充为白色背景
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, imgWidth, imgHeight);

			// 画100个噪点(颜色及位置随机)
			for (int i = 0; i < 100; i++) {
				// 随机颜色
				int rInt = random.nextInt(255);
				int gInt = random.nextInt(255);
				int bInt = random.nextInt(255);

				graphics.setColor(new Color(rInt, gInt, bInt));

				// 随机位置
				int xInt = random.nextInt(imgWidth - 3);
				int yInt = random.nextInt(imgHeight - 2);

				// 随机旋转角度
				int sAngleInt = random.nextInt(360);
				int eAngleInt = random.nextInt(360);

				// 随机大小
				int wInt = random.nextInt(6);
				int hInt = random.nextInt(6);

				graphics.fillArc(xInt, yInt, wInt, hInt, sAngleInt, eAngleInt);

				// 画5条干扰线
				if (i % 20 == 0) {
					int xInt2 = random.nextInt(imgWidth);
					int yInt2 = random.nextInt(imgHeight);
					graphics.drawLine(xInt, yInt, xInt2, yInt2);
				}
			}
		}
	}
}
