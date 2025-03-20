package base.message;

public enum CustomMessage {
    GREETING("안녕하세요!"),
    EXIT("프로그램이 종료됩니다."),
    NOT_UNDERSTAND("존재하지 않는 명령어 입니다."),

    QUEST_READ_POST("어떤 게시물을 조회할까요? "),
    QUEST_DELETE_POST("어떤 게시물을 삭제할까요? "),
    QUEST_UPDATE_POST("어떤 게시물을 수정할까요? "),
    QUEST_DELETE_BOARD("어떤 게시판을 삭제할까요? "),

    SUCCESS_DELETE_POST("%d번 게시물이 성공적으로 삭제되었습니다!"),
    SUCCESS_UPDATE_POST("%d번 게시물이 성공적으로 수정되었습니다!"),
    SUCCESS_UPDATE_BOARD("%d번 게시판이 성공적으로 수정되었습니다."),
    SUCCESS_DELETE_BOARD("%d번 게시판이 성공적으로 삭제되었습니다."),
    SUCCESS_LOGIN("로그인 성공"),
    SUCCESS_LOGOUT("로그아웃 성공"),
    SUCCESS_UPDATE_ACCOUNT("%d번 계정이 성공적으로 수정되었습니다. "),
    SUCCESS_DELETE_ACCOUNT("%d번 계정이 성공적으로 삭제되었습니다. "),

    FAIL_POST("%d번 게시글은 존재하지 않습니다."),
    FAIL_BOARD("%d번 게시판은 존재하지 않습니다."),
    FAIL_LOGIN("로그인 실패"),
    FAIL_LOGOUT("이미 로그아웃 상태입니다. "),
    FAIL_ACCOUNT("존재하지 않는 계정입니다. "),

    PREVIEW_UPDATE_POST("%d번 게시물을 수정합니다."),
    PREVIEW_UPDATE_BOARD("%d번 게시판을 수정/생성합니다."),
    PREVIEW_UPDATE_ACCOUNT("%d번 계정을 수정합니다. "),
    PREVIEW_TOTAL_POST_CNT("총 게시글은 %d개 작성되어있습니다. "),

    TABLE_HEAD_ROW_IN_POST("글 번호 / 글 제목 / 작성일"),
    TABLE_DATA_ROW_IN_POST("%s / %s / %s"),

    INPUT_TITLE("제목 : "),
    INPUT_CONTENT("내용 : "),
    INPUT_ID("아이디 : "),
    INPUT_PW("비밀번호 : "),
    INPUT_NAME("이름 : "),
    INPUT_NICKNAME("닉네임 : "),
    INPUT_EMAIL("이메일 : "),

    FORMAT_ACCOUNT("이름 : %s\n역할 : %s"),
    FORMAT_FULL_ACCOUNT("%d번 회원\n계정 : %s\n이메일 : %s\n가입일 : %s"),
    FORMAT_BRIEF_POST("제목 : %s\n내용 : %s"),
    FORMAT_GENERAL_POST("%d번 게시글\n제목 : %s\n내용 : %s"),
    FORMAT_FULL_POST("%d번 게시글\n작성일 : %s\n수정일 : %s\n제목 : %s\n내용 : %s");


    private final String message;

    CustomMessage(String message) {
        this.message = message;
    }

    public void printMessage(boolean isLineBreak) {
        if (isLineBreak) {
            System.out.println(message);
            return;
        }
        System.out.printf(message);
    }

    public void printMessage(Object... parameters) {
        System.out.println(String.format(message, parameters));
    }
}
