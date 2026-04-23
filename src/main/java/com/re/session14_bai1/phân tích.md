Phân tích :


- Dựa vào đoạn code ta có thể thấy khi thực hiện giao dịch thì chỉ bắn ra excepion mà không thực hiện việc hoàn trả ,
- trong đó việc rollback khi sảy ra lỗi rất quan trong , sau khi thực thi cững không có commit, -> không áp dụng tracsaction nào việc sử lý giao dịch