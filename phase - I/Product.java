import java.io.File;
import java.util.Scanner;

public class Product {
		private String code;
		private String type;
		
		

		public Product(String code, String type) {
			
			this.code = code;
			this.type = type;
		}
		
			
			
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
		
		@Override
		public String toString() {
			return "Product [code=" + code + ", type=" + type + "]";
		}
		
		
		
		
		}