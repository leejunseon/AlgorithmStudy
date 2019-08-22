/* http://tech.kakao.com/2018/09/21/kakao-blind-recruitment-for2019-round-1/ */
//문제 조건 꼼꼼히 체크하기

import java.util.*;
import java.io.*;

public class Solution {
	public static List<page> infos;
	public static Map<String,Double> linkMap;

	public static void main(String[] args) {
		String[] pages= {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>"
						, "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
		System.out.println(solution("Muzi",pages));
	}

	public static int solution(String word, String[] pages) {
		infos=new ArrayList<page>();
		linkMap=new HashMap<String,Double>();
		StringTokenizer lineSplit;
		StringTokenizer urlFinder;
		StringTokenizer linkFinder;
		StringTokenizer matchingFinder;
		int index=0;
		String url="";
		int matching=0;
		List<String> externalLinks;
		word=word.toLowerCase();

		//입력 page정보 추출
		for(int i=0;i<pages.length;i++) {
			index=i;//인덱스
			matching=0;//매칭 갯수
			externalLinks=new ArrayList<String>();//외부링크 갯수
			lineSplit=new StringTokenizer(pages[i],"\n");//라인단위로 자름

			while(lineSplit.hasMoreTokens()) {
				String line=lineSplit.nextToken().toLowerCase();//한 줄

				//url찾기
				if(line.contains("meta")&&line.contains("content")) {
					urlFinder=new StringTokenizer(line,"\"");
					while(urlFinder.hasMoreTokens()) {
						String token=urlFinder.nextToken();
						if(token.contains("https://")) {
							url=token;
							linkMap.put(url, 0.0);
							break;
						}
					}
				}

				//외부 링크 여부
				if(line.contains("<a href")) {
					linkFinder=new StringTokenizer(line,"\"");
					while(linkFinder.hasMoreTokens()) {
						String token=linkFinder.nextToken();
						if(token.contains("https://")) {
							externalLinks.add(token);
						}
					}
				}

				//matching 계산
				if(line.contains(word)) {
					String delim= "";
					for(int ascii=0;ascii<=127;ascii++) {
						if(ascii<97||ascii>122)
							delim+=String.valueOf((char)ascii);
					}
					matchingFinder=new StringTokenizer(line,delim);
					while(matchingFinder.hasMoreTokens()) {
						String str=matchingFinder.nextToken();
						if(str.equals(word))
							matching++;
					}
				}
			}

			infos.add(new page(index,url,matching,0,externalLinks));
		}

		//링크점수 계산
		for(int i=0;i<infos.size();i++) {
			page p=infos.get(i);
			List<String> links=p.externalLinks;
			for(int j=0;j<links.size();j++) {
				if(linkMap.containsKey(links.get(j))) {
					double pre=linkMap.get(links.get(j));
					double score=(double)p.matching/links.size();//매칭 갯수 / 외부링크 갯수
					linkMap.put(links.get(j), pre+score);
				}
			}
		}

		//총점수 계산
		for(int i=0;i<infos.size();i++) {
			page p=infos.get(i);
			p.link=p.matching+linkMap.get(p.url);
		}

		Collections.sort(infos,new Comparator<page>() {

			@Override
			public int compare(page arg0, page arg1) {
				// TODO Auto-generated method stub
				if(arg0.link!=arg1.link) {
					return arg1.link>arg0.link?1:-1;
				}
				else
					return arg0.idx-arg1.idx;
			}

		});

		return infos.get(0).idx;
	}
}

class page{
	int idx;
	String url;
	int matching;//매칭 갯수
	double link;//총 점수
	List<String> externalLinks;
	page(int idx,String url,int matching,double link,List<String> externalLinks){
		this.idx=idx;
		this.url=url;
		this.matching=matching;
		this.link=link;
		this.externalLinks=externalLinks;
	}
}
