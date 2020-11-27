
class Word implements Comparable<Word>{
	String content;
	Integer value;
	
	public Word() {
	}
	
	public Word(String c,Integer v) {
		this.content=c;
		this.value=v;
	}
	
	/*将自己与another比较，若返回>0则升序排序*/
	public int compareTo(Word another) {
		int i=value-another.value;
		if(i==0) {
			return -content.compareTo(another.content);
		}else {
			return -i;
		}
	}
}