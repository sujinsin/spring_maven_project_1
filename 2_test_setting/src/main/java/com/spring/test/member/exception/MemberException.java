package com.spring.test.member.exception;

public class MemberException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 예외처리 안해주려고 언체크드 조상인 runtime익셉션 익스텐즈 해줌 
	   public MemberException() {}
	   public MemberException(String msg) {
	      super(msg);
	      this.toString();
	      this.getMessage();
	      this.getStackTrace();
	   }
}
