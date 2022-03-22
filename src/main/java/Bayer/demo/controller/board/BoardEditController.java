package Bayer.demo.controller.board;

import Bayer.demo.domain.board.Board;
import Bayer.demo.dto.board.BoardEditDto;
import Bayer.demo.repository.BoardRepository;
import Bayer.demo.repository.UserRepository;
import Bayer.demo.service.board.BoardEditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardEditController {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardEditService boardEditService;

    @GetMapping("/notice/{id}/edit")
    public String noticeEdit(@PathVariable Long id, Model model, HttpSession session){
        Board board = boardRepository.getById(id);
        Long loginId = userRepository.findByNickname(session.getAttribute("loginUser").toString()).orElse(null).getId();
        if (!board.getUser().getId().equals(loginId)) {
            model.addAttribute("editFail","fail");
        }
        model.addAttribute("boardEditDto", board);
        return "/board/notice_edit";
    }

    @PostMapping("/notice/{id}/edit")
    public String noticeEdit(BoardEditDto boardEditDto){
        boardEditService.editBoard(boardEditDto);
        return "redirect:/board/notice/{id}";
    }
}
